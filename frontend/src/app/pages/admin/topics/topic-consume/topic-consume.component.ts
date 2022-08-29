import {Component, OnDestroy, OnInit} from '@angular/core';
import {TopicService} from "../topic.service";
import {Subject} from "rxjs";
import {NbDialogRef} from "@nebular/theme";
import {WebSocketSubject} from "rxjs/webSocket";
import {map, takeUntil, tap} from "rxjs/operators";
import {LocalDataSource} from "ng2-smart-table";
import * as flatten from 'flat';
import * as _ from 'lodash';

@Component({
  selector: 'ngx-topic-consume',
  template: `
    <nb-card>
      <nb-card-header>
        <button nbButton type="button" status="primary" size="small" (click)="closeDialog($event)">
          <nb-icon icon="close-outline"></nb-icon>
        </button>
      </nb-card-header>
      <nb-card-body>
        <div *ngIf="dataLoaded">
          <nb-form-field>

            <nb-tag-list (tagRemove)="onTagRemove($event)">
              <nb-tag *ngFor="let col of gridSettings.columns | keyvalue" [text]="col?.key" removable></nb-tag>
              <input type="text" nbTagInput (tagAdd)="onTagAdd($event.value)" [nbAutocomplete]="autocomplete" fullWidth>
            </nb-tag-list>
            <nb-icon nbSuffix icon="search" pack="eva"></nb-icon>
          </nb-form-field>

          <nb-autocomplete #autocomplete (selectedChange)="onTagAdd($event)">
            <nb-option *ngFor="let col of availableCols" [value]="col">{{ col }}</nb-option>
          </nb-autocomplete>

          <ng2-smart-table [settings]="gridSettings" [source]="source"></ng2-smart-table>

        </div>
      </nb-card-body>
    </nb-card>`,
  styleUrls: ['./topic-consume.component.scss']
})
export class TopicConsumeComponent implements OnInit, OnDestroy {

  destroy$: Subject<void> = new Subject<void>();
  topicNameParam: string;
  datasourceConfig: [string, WebSocketSubject<any>];
  source: LocalDataSource = new LocalDataSource();

  dataLoaded = false;

  constructor(private topicService: TopicService,
              protected dialogRef: NbDialogRef<TopicConsumeComponent>) {
  }

  availableCols: string[] = [];

  settingsSeed =
    {
      mode: 'external',
      actions: {
        delete: false,
        edit: false,
        add: false
      },
      columns: {},
    };

  gridSettings: { mode?: string; columns?: {}; actions?: { add: boolean; edit: boolean; delete: boolean } } = {};

  ngOnInit(): void {
    this.datasourceConfig = this.topicService.consume(this.topicNameParam);

    let data: any[] = [];

    this.datasourceConfig[1].pipe(
      takeUntil(this.destroy$),
      tap((data) => {
        const dataCols = flatten(JSON.parse(JSON.stringify(data)));
        const gridCols = {};

        Object.keys(dataCols).forEach((key) => {
          const keyName = key.split(".").pop();
          gridCols[keyName] = {
            title: keyName,
            type: 'String'
          };
          this.availableCols.push(keyName);
        });

        this.gridSettings = this.populateCols(this.settingsSeed, this.availableCols);

      }),
      map((record) => {
        const gridData = flatten(JSON.parse(JSON.stringify(record)));
        let gridData2 = {};

        Object.keys(gridData).forEach((key) => {
          const keyName = key.split(".").pop();
          gridData2[keyName] = gridData[key];
        });

        data = [...data, gridData2];
        this.source.load(data);
        this.dataLoaded = true;
      })
    ).subscribe();

  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  closeDialog($event) {
    this.dialogRef.close();
    this.topicService.closeSocket(this.datasourceConfig[0]);
  }

  onTagAdd($event) {
    const tag = $event;
    const settings = _.cloneDeep(this.gridSettings);
    settings.columns[tag] = {
      title: tag,
      type: 'String'
    }
    this.availableCols = this.availableCols.filter(item => item!=tag);
    this.gridSettings = settings;
  }

  onTagRemove($event) {
    const tag = $event.text;
    const settings = _.cloneDeep(this.gridSettings);
    delete settings.columns[tag];
    this.availableCols.push(tag);
    this.gridSettings = settings;
  }

  private populateCols(settings: { mode: string; columns: {}; actions: { add: boolean; edit: boolean; delete: boolean } }, availableCols: string[]) {
    const firstTen: string[] = Array.from(availableCols).slice(0, 5);
    const gridSettings = _.cloneDeep(settings);

    this.availableCols = this.availableCols.filter(item => !firstTen.includes(item));

    firstTen.forEach(item => gridSettings.columns[item] = {
      title: item,
      type: 'String'
    });

    return gridSettings;
  }
}

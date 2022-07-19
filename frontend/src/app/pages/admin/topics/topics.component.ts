import {Component, OnInit} from '@angular/core';
import {KafkaTopicDTO} from "../../../@types/kafka-models";
import {LocalDataSource} from "ng2-smart-table";
import {TopicRepository} from "../../../store/repositories/topic.repository";
import {KafkaAdminService} from "../kafka-admin.service";
import {map} from "rxjs/operators";
import {NbDialogService} from "@nebular/theme";
import {CreateTopicComponent} from "./create-topic/create-topic.component";

declare interface TopicGridData {
  name: string;
  numOfPartitions: number;
  internal: boolean;
};

@Component({
  selector: 'ngx-topics',
  template: `
    <nb-card>
      <nb-card-header>
        Topics
      </nb-card-header>
      <nb-card-body>
        <ng2-smart-table [settings]="settings" [source]="source" (delete)="onDelete($event)"></ng2-smart-table>
      </nb-card-body>
      <nb-card-footer>
        <button nbButton type="button" status="primary" (click)="showDialog()">Create Topic</button>
      </nb-card-footer>
    </nb-card>`,
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {

  gridData: any[];
  source: LocalDataSource = new LocalDataSource();

  settings =
    {
      mode: 'external',
      actions: {
        delete: true,
        edit: true
      },
      add: {
        addButtonContent: '<i class="nb-plus"></i>',
        createButtonContent: '<i class="nb-checkmark"></i>',
        cancelButtonContent: '<i class="nb-close"></i>',
      },
      edit: {
        editButtonContent: '<i class="nb-edit"></i>',
        saveButtonContent: '<i class="nb-checkmark"></i>',
        cancelButtonContent: '<i class="nb-close"></i>',
      },
      delete: {
        deleteButtonContent: '<i class="nb-trash"></i>',
        confirmDelete: false,
      },
      columns: {
        name: {
          title: 'Name',
          type: 'String',
        },
        numOfPartitions: {
          title: 'Number Of Partitions',
          type: 'String',
        },
        internal: {
          title: 'Is Internal',
          type: 'boolean',
        }
      },
    };

  constructor(private service: KafkaAdminService,
              private topicRepository: TopicRepository,
              private dialogService: NbDialogService) {
    this.getData();
  }

  getData() {
    this.topicRepository.topics$
      .pipe(
        map((data: KafkaTopicDTO[]) => {
          const gridData: TopicGridData[] = data.map(item => <TopicGridData>{
            name: item.name,
            internal: item.internal,
            numOfPartitions: item.topicPartitions.length
          });
          this.source.load(gridData);
        })
      ).subscribe()
  }

  onDelete($event) {
    const topicName: string = $event.data.name;
    this.service.deleteTopic(topicName).subscribe((data) => this.getData());
  }

  showDialog() {
    this.dialogService.open(CreateTopicComponent);
  }

  ngOnInit(): void {
  }

}

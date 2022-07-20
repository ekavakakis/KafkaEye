import {Component, OnDestroy, OnInit} from '@angular/core';
import {ConsumerGroupRepository} from "../../../../store/repositories/consumer-group.repository";
import {Subject} from "rxjs";
import {map, takeUntil} from "rxjs/operators";
import {ConsumerGroupDTO} from "../../../../@types/kafka-models";
import * as _ from 'lodash';

@Component({
  selector: 'ngx-consumer-group-overview',
  templateUrl: './consumer-group-overview.component.html',
  styleUrls: ['./consumer-group-overview.component.scss']
})
export class ConsumerGroupOverviewComponent implements OnInit, OnDestroy {

  dataPerGroup: any = {};
  destroy$: Subject<void> = new Subject<void>();

  constructor(public consumerGroupRepo: ConsumerGroupRepository) {}

  ngOnInit(): void {
    this.consumerGroupRepo.groups$.pipe(
      takeUntil(this.destroy$),
      map((data: ConsumerGroupDTO[]) => {
        const items = _.groupBy(data, 'state');
        Object.keys(items)
          .forEach(key => {
            this.dataPerGroup[key] = items[key].length;
          });
      })
    ).subscribe()
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

}

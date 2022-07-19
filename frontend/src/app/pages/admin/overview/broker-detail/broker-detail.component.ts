import { Component, OnInit } from '@angular/core';
import {ClusterRepository} from "../../../../store/repositories/cluster.repository";

@Component({
  selector: 'ngx-broker-detail',
  template: `
    <nb-card [size]="'tiny'">
      <nb-list>
        <nb-list-item *ngFor="let broker of (clusterRepo.cluster$ | async)?.nodes">
          {{ broker.host }}:{{ broker.port }}
        </nb-list-item>
      </nb-list>
    </nb-card>
  `,
  styleUrls: ['./broker-detail.component.scss']
})
export class BrokerDetailComponent implements OnInit {

  constructor(public clusterRepo: ClusterRepository) { }

  ngOnInit(): void {}
}

import {Component, OnInit} from '@angular/core';
import {ClusterRepository} from "../../../../store/repositories/cluster.repository";

@Component({
  selector: 'ngx-broker-overview',
  templateUrl: './broker-overview.component.html',
  styleUrls: ['./broker-overview.component.scss']
})
export class BrokerOverviewComponent implements OnInit {

  constructor(public clusterRepo: ClusterRepository) {
  }

  ngOnInit(): void {
  }

}

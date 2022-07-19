import { Component, OnInit } from '@angular/core';
import {DescribeClusterDTO} from "../../../@types/kafka-models";
import {KafkaAdminService} from "../kafka-admin.service";
import {first, map} from "rxjs/operators";

@Component({
  selector: 'ngx-cluster-description',
  templateUrl: './cluster.component.html',
  styleUrls: ['./cluster.component.scss']
})
export class ClusterComponent implements OnInit {

  constructor() {}

  ngOnInit(): void {}

}

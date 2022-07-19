import { Component, OnInit } from '@angular/core';
import {ConsumerGroupRepository} from "../../../../store/repositories/consumer-group.repository";

@Component({
  selector: 'ngx-consumer-group-overview',
  templateUrl: './consumer-group-overview.component.html',
  styleUrls: ['./consumer-group-overview.component.scss']
})
export class ConsumerGroupOverviewComponent implements OnInit {

  constructor(public consumerGroupRepo: ConsumerGroupRepository) { }

  ngOnInit(): void {
  }

}

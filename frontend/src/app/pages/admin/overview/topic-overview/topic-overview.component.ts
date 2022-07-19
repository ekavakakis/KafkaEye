import {Component, OnInit} from '@angular/core';
import {KafkaTopicDTO} from "../../../../@types/kafka-models";
import {map} from "rxjs/operators";
import * as _ from 'lodash';
import {TopicRepository} from "../../../../store/repositories/topic.repository";

@Component({
  selector: 'ngx-topic-overview',
  templateUrl: './topic-overview.component.html',
  styleUrls: ['./topic-overview.component.scss']
})
export class TopicOverviewComponent implements OnInit {

  topics: KafkaTopicDTO[];
  numOfPartitions: number;

  constructor(private topicRepository: TopicRepository) {
    topicRepository.topics$.pipe(
      map((data: KafkaTopicDTO[]) => {
        this.topics = data;
        this.numOfPartitions = _.sumBy(data, (topicDTO: KafkaTopicDTO) => topicDTO.topicPartitions.length);
      })
    ).subscribe()
  }

  ngOnInit(): void {
  }

}

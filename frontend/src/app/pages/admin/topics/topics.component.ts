import { Component, OnInit } from '@angular/core';
import {KafkaAdminService} from "../kafka-admin.service";
import {KafkaTopic} from "../../../@types/kafka-models";
import {LocalDataSource} from "ng2-smart-table";

@Component({
  selector: 'ngx-topics',
  template: `
    <nb-card>
      <nb-card-header>
        Topics
      </nb-card-header>
    <nb-card-body>
        <ng2-smart-table [settings]="settings" [source]="source"></ng2-smart-table>
    </nb-card-body>
  </nb-card>`,
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {

  topics: KafkaTopic[]
  source: LocalDataSource = new LocalDataSource();

  settings = {
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
      confirmDelete: true,
    },
    columns: {
      topicId: {
        title: 'Topic Id',
        type: 'string',
      },
      name: {
        title: 'Name',
        type: 'String',
      },
      internal: {
        title: 'Is Internal',
        type: 'boolean',
      }
    },
  };

  constructor(private service: KafkaAdminService) {
    service.getTopics().subscribe(data => {
      this.source.load(data);
    });
  }

  ngOnInit(): void {}

}

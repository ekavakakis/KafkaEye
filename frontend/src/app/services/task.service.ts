import { Injectable } from '@angular/core';
import {KafkaAdminService} from "../pages/admin/kafka-admin.service";
import {first} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private kafkaAdminService: KafkaAdminService) {
    this.runTasks();
  }

  storeSubscriptions() {
    this.kafkaAdminService.populateTopicStore().pipe(first()).subscribe();
    this.kafkaAdminService.populateClusterStore().pipe(first()).subscribe();
    this.kafkaAdminService.populateConsumerGroupStore().pipe(first()).subscribe();
  }

  runTasks() {
    this.storeSubscriptions();
    setInterval(() => {
      this.storeSubscriptions();
    }, 10000)
  }

}

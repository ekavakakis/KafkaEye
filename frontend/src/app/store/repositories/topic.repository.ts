import {createStore} from '@ngneat/elf';
import {KafkaTopicDTO} from "../../@types/kafka-models";
import {selectAllEntities, setEntities, withEntities} from "@ngneat/elf-entities";
import {Injectable} from "@angular/core";


const store = createStore(
  {name: 'topics'},
  withEntities<KafkaTopicDTO>()
);

@Injectable({providedIn: 'root'})
export class TopicRepository {

  topics$ = store.pipe(selectAllEntities())

  public updateEntities(topics: KafkaTopicDTO[]) {
    store.update(setEntities(topics));
  }

}

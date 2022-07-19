import {createStore} from '@ngneat/elf';
import {ConsumerGroupDTO} from "../../@types/kafka-models";
import {selectAllEntities, setEntities, withEntities} from "@ngneat/elf-entities";
import {Injectable} from "@angular/core";


const store = createStore(
  {name: 'consumer-groups'},
  withEntities<ConsumerGroupDTO>()
);

@Injectable({providedIn: 'root'})
export class ConsumerGroupRepository {

  groups$ = store.pipe(selectAllEntities())

  public update(groups: ConsumerGroupDTO[]) {
    store.update(setEntities(groups));
  }

}

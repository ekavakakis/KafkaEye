import {createStore, select, withProps} from '@ngneat/elf';
import {DescribeClusterDTO} from "../../@types/kafka-models";
import {Injectable} from "@angular/core";

interface ClusterProps {
  cluster: DescribeClusterDTO | null
}

const store = createStore(
  {name: 'cluster'},
  withProps<ClusterProps>({cluster: null})
);

@Injectable({providedIn: 'root'})
export class ClusterRepository {

  cluster$ = store.pipe(select((state => state.cluster)));

  public update(cluster: DescribeClusterDTO) {
    store.update((state) => ({...state, cluster}));
  }

}

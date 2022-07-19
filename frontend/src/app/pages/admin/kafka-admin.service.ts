import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ConsumerGroupDTO, CreateTopicDTO, DescribeClusterDTO, KafkaTopicDTO} from "../../@types/kafka-models";
import {TopicRepository} from "../../store/repositories/topic.repository";
import {first, tap} from "rxjs/operators";
import {ClusterRepository} from "../../store/repositories/cluster.repository";
import {ConsumerGroupRepository} from "../../store/repositories/consumer-group.repository";

@Injectable({
  providedIn: 'root'
})
export class KafkaAdminService {

  serviceUrl: string = 'http://localhost:8080/admin';

  constructor(private httpClient: HttpClient,
              private topicRepository: TopicRepository,
              private clusterRepository: ClusterRepository,
              private consumerGroupRepository: ConsumerGroupRepository) {
  }

  public getTopics(): Observable<KafkaTopicDTO[]> {
    const url: string = `${this.serviceUrl}/list-topics`;
    return this.httpClient.get<KafkaTopicDTO[]>(url);
  }

  public populateTopicStore(): Observable<KafkaTopicDTO[]> {
    return this.getTopics()
      .pipe(
        first(),
        tap((data) => this.topicRepository.updateEntities(data))
      );
  }

  public getClusterDescription(): Observable<DescribeClusterDTO> {
    const url: string = `${this.serviceUrl}/describe-cluster`;
    return this.httpClient.get<DescribeClusterDTO>(url);
  }

  public populateClusterStore(): Observable<DescribeClusterDTO> {
    return this.getClusterDescription()
      .pipe(
        first(),
        tap((data) => this.clusterRepository.update(data))
      );
  }

  public getConsumerGroups(): Observable<ConsumerGroupDTO[]> {
    const url: string = `${this.serviceUrl}/consumer-groups`;
    return this.httpClient.get<ConsumerGroupDTO[]>(url);
  }

  public populateConsumerGroupStore(): Observable<ConsumerGroupDTO[]> {
    return this.getConsumerGroups()
      .pipe(
        first(),
        tap((data) => this.consumerGroupRepository.update(data))
      );
  }

  public createTopic(createTopicDTO: CreateTopicDTO): Observable<void> {
    const url: string = `${this.serviceUrl}/create-topic`;
    return this.httpClient.post<void>(url, createTopicDTO)
      .pipe(
        tap(() => this.populateTopicStore().subscribe())
      );
  }

  public deleteTopic(topicName: String): Observable<void> {
    const url: string = `${this.serviceUrl}/topic/${topicName}`;
    return this.httpClient.delete<void>(url).pipe(
      tap(() => this.populateTopicStore().subscribe())
    );
  }

}

import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DescribeClusterDTO, KafkaTopicDTO} from "../../@types/kafka-models";
import {TopicRepository} from "../../store/repositories/topic.repository";
import {first, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class KafkaAdminService {

  serviceUrl: string = 'http://localhost:8080/admin';

  constructor(private httpClient: HttpClient, private topicRepository: TopicRepository) {
  }

  public getTopics(): Observable<KafkaTopicDTO[]> {
    const url: string = `${this.serviceUrl}/list-topics`;
    return this.httpClient.get<KafkaTopicDTO[]>(url);
  }

  public populateTopicStore(): Observable<KafkaTopicDTO[]> {
    const url: string = `${this.serviceUrl}/list-topics`;
    return this.httpClient.get<KafkaTopicDTO[]>(url)
      .pipe(
        first(),
        tap((data) => this.topicRepository.updateEntities(data))
      );
  }

  public getClusterDescription(): Observable<DescribeClusterDTO> {
    const url: string = `${this.serviceUrl}/describe-cluster`;
    return this.httpClient.get<DescribeClusterDTO>(url);
  }

  public deleteTopic(topicName: String): Observable<void> {
    const url: string = `${this.serviceUrl}/topic/${topicName}`;
    return this.httpClient.delete<void>(url);
  }

}

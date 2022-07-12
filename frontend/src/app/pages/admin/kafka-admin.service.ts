import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DescribeClusterDTO, KafkaTopic} from "../../@types/kafka-models";

@Injectable({
  providedIn: 'root'
})
export class KafkaAdminService {

  serviceUrl: string = 'http://localhost:8080/admin';

  constructor(private httpClient: HttpClient) {}

  public getTopics(): Observable<KafkaTopic[]> {
    const url: string = `${this.serviceUrl}/list-topics`;
    return this.httpClient.get<any[]>(url);
  }

  public getClusterDescription(): Observable<DescribeClusterDTO> {
    const url: string = `${this.serviceUrl}/describe-cluster`;
    return this.httpClient.get<DescribeClusterDTO>(url);
  }

}

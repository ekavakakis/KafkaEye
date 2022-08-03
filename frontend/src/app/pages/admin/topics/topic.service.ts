import {Injectable} from '@angular/core';
import {webSocket, WebSocketSubject} from "rxjs/webSocket";
import {v4 as uuid} from 'uuid';


@Injectable({
  providedIn: 'root'
})
export class TopicService {

  serviceUrl: string = 'ws://localhost:8080/consume';
  dataSources: any = {};

  public consume(name: string): [string, WebSocketSubject<any>] {
    const socketId: string = uuid();
    const ws = webSocket(`${this.serviceUrl}/${name}`);
    this.dataSources[socketId] = ws;
    return [socketId, ws];
  }

  public closeSocket(socketId: string) {
    (this.dataSources[socketId] as WebSocketSubject<any>).unsubscribe();
  }

}

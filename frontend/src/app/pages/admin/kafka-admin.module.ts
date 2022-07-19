import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TopicsComponent} from './topics/topics.component';
import {KafkaAdminComponent} from './kafka-admin.component';
import {NbButtonModule, NbCardModule, NbDialogModule, NbInputModule, NbListModule} from '@nebular/theme';
import {KafkaAdminService} from './kafka-admin.service';
import {RouterModule} from '@angular/router';
import {OverviewComponent} from './overview/overview.component';
import {KafkaAdminRoutingModule} from './kafka-admin-routing.module';
import {Ng2SmartTableModule} from "ng2-smart-table";
import {ClusterComponent} from './cluster/cluster.component';
import {TopicOverviewComponent} from './overview/topic-overview/topic-overview.component';
import {BrokerOverviewComponent} from './overview/broker-overview/broker-overview.component';
import {BrokerDetailComponent} from './overview/broker-detail/broker-detail.component';
import {ConsumerGroupOverviewComponent} from './overview/consumer-group-overview/consumer-group-overview.component';
import {CreateTopicComponent} from './topics/create-topic/create-topic.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    TopicsComponent,
    KafkaAdminComponent,
    OverviewComponent,
    ClusterComponent,
    TopicOverviewComponent,
    BrokerOverviewComponent,
    BrokerDetailComponent,
    ConsumerGroupOverviewComponent,
    CreateTopicComponent],
  imports: [
    CommonModule,
    RouterModule,
    NbCardModule,
    NbListModule,
    NbButtonModule,
    KafkaAdminRoutingModule,
    Ng2SmartTableModule,
    NbDialogModule,
    NbInputModule,
    ReactiveFormsModule,
  ],
  providers: [KafkaAdminService],
})
export class KafkaAdminModule {
}

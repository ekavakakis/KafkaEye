import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TopicsComponent} from './topics/topics.component';
import {KafkaAdminComponent} from './kafka-admin.component';
import {NbCardModule, NbListModule} from '@nebular/theme';
import {KafkaAdminService} from './kafka-admin.service';
import {RouterModule} from '@angular/router';
import { OverviewComponent } from './overview/overview.component';
import {KafkaAdminRoutingModule} from './kafka-admin-routing.module';
import {Ng2SmartTableModule} from "ng2-smart-table";
import { ClusterComponent } from './cluster/cluster.component';

@NgModule({
  declarations: [
    TopicsComponent,
    KafkaAdminComponent,
    OverviewComponent,
    ClusterComponent],
  imports: [
    CommonModule,
    RouterModule,
    NbCardModule,
    NbListModule,
    KafkaAdminRoutingModule,
    Ng2SmartTableModule
  ],
  providers: [KafkaAdminService],
})
export class KafkaAdminModule { }

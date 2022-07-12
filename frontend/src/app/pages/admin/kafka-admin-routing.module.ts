import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TopicsComponent} from "./topics/topics.component";
import {KafkaAdminComponent} from "./kafka-admin.component";
import {OverviewComponent} from "./overview/overview.component";

const routes: Routes = [{
  path: '',
  component: KafkaAdminComponent,
  children: [
    {
      path: 'overview',
      component: OverviewComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class KafkaAdminRoutingModule { }

export const routedComponents = [
  TopicsComponent,
];

import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreateTopicDTO, KafkaTopicDTO} from "../../../../@types/kafka-models";
import {KafkaAdminService} from "../../kafka-admin.service";
import {first, map, tap} from "rxjs/operators";
import {NbDialogRef} from "@nebular/theme";

@Component({
  selector: 'ngx-create-topic',
  template: `
    <form [formGroup]="topicForm" (ngSubmit)="onSubmit(topicForm.value)">
      <nb-card>
        <nb-card-header>
          Topic Details
        </nb-card-header>
        <nb-card-body>
          <div class="row">
            <div class="col-sm">
              <div class="form-group">
                <label for="name" class="label">First Name</label>
                <input id="name" type="text" nbInput fullWidth formControlName="name" placeholder="Name">
              </div>
            </div>
            <div class="col-sm">
              <div class="form-group">
                <label for="replicationFactor" class="label">First Name</label>
                <input id="replicationFactor" type="number" nbInput fullWidth formControlName="replicationFactor"
                       placeholder="Replication Factor">
              </div>
            </div>
            <div class="col-sm">
              <div class="form-group">
                <label for="numberOfPartitions" class="label">First Name</label>
                <input id="numberOfPartitions" type="number" nbInput fullWidth formControlName="partitions"
                       placeholder="Number Of Partitions">
              </div>
            </div>
          </div>
        </nb-card-body>
        <nb-card-footer>
          <button nbButton type="submit" status="primary">Submit</button>
        </nb-card-footer>
      </nb-card>
    </form>
  `,
  styleUrls: ['./create-topic.component.scss']
})
export class CreateTopicComponent implements OnInit {

  topicForm: FormGroup;

  constructor(public fb: FormBuilder,
              private adminService: KafkaAdminService,
              protected dialogRef: NbDialogRef<CreateTopicComponent>) {
  }

  ngOnInit(): void {
    this.topicForm = this.fb.group({
      name: this.fb.control(null, Validators.required),
      partitions: this.fb.control(null, Validators.required),
      replicationFactor: this.fb.control(null, Validators.required)
    });
  }

  onSubmit($event) {
    const payload: CreateTopicDTO = <CreateTopicDTO>{
      name: $event.name,
      partitions: $event.partitions,
      replicationFactor: $event.replicationFactor
    };
    this.adminService.createTopic(payload).pipe(
      first(),
      tap(() => this.dialogRef.close())
    ).subscribe();
  }

}

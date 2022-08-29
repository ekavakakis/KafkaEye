import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopicConsumeComponent } from './topic-consume.component';

describe('TopicConsumeComponent', () => {
  let component: TopicConsumeComponent;
  let fixture: ComponentFixture<TopicConsumeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TopicConsumeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TopicConsumeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentpComponent } from './appointmentp.component';

describe('AppointmentpComponent', () => {
  let component: AppointmentpComponent;
  let fixture: ComponentFixture<AppointmentpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

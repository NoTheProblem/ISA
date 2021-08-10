import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentDermaComponent } from './appointment-derma.component';

describe('AppointmentDermaComponent', () => {
  let component: AppointmentDermaComponent;
  let fixture: ComponentFixture<AppointmentDermaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentDermaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentDermaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

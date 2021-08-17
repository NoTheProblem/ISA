import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PromotionsPatientComponent } from './promotions-patient.component';

describe('PromotionsPatientComponent', () => {
  let component: PromotionsPatientComponent;
  let fixture: ComponentFixture<PromotionsPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PromotionsPatientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PromotionsPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

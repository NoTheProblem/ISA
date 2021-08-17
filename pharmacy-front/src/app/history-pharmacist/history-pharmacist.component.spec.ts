import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryPharmacistComponent } from './history-pharmacist.component';

describe('HistoryPharmacistComponent', () => {
  let component: HistoryPharmacistComponent;
  let fixture: ComponentFixture<HistoryPharmacistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryPharmacistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryPharmacistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

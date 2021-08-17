import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryDermaComponent } from './history-derma.component';

describe('HistoryDermaComponent', () => {
  let component: HistoryDermaComponent;
  let fixture: ComponentFixture<HistoryDermaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryDermaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryDermaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

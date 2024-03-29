import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableMedicinesComponent } from './available-medicines.component';

describe('AvailableMedicinesComponent', () => {
  let component: AvailableMedicinesComponent;
  let fixture: ComponentFixture<AvailableMedicinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvailableMedicinesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailableMedicinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

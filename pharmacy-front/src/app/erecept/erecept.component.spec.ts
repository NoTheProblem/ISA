import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EReceptComponent } from './erecept.component';

describe('EReceptComponent', () => {
  let component: EReceptComponent;
  let fixture: ComponentFixture<EReceptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EReceptComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EReceptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

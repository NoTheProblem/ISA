import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeEvaluationComponent } from './change-evaluation.component';

describe('ChangeEvaluationComponent', () => {
  let component: ChangeEvaluationComponent;
  let fixture: ComponentFixture<ChangeEvaluationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeEvaluationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeEvaluationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

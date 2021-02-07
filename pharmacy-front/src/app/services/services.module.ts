import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PharmacyService} from './pharmacy.service';
import {MedicineService} from './medicine.service';
import {DermatologistService} from './dermatologist.service';
import {ExaminationService} from './examination.service';
import {CounselingComponent} from '../counseling/counseling.component';
import {PatientService} from './patient.service';
import {PromotionService} from './promotion.service';
import {AbsenceService} from './absence.service';

@NgModule({
  declarations: [],
  providers: [
    PharmacyService,
    MedicineService,
    DermatologistService,
    ExaminationService,
    CounselingComponent,
    MedicineService,
    PatientService,
    PromotionService,
    AbsenceService
  ],
  imports: [
    CommonModule
  ]
})
export class ServicesModule {
}

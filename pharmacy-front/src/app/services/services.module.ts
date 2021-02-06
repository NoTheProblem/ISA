import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PharmacyService} from './pharmacy.service';
import {MedicineService} from './medicine.service';
import {DermatologistService} from './dermatologist.service';
import {ExaminationService} from './examination.service';
import {CounselingComponent} from '../counseling/counseling.component';
import {PatientService} from './patient.service';

@NgModule({
  declarations: [],
  providers: [
    PharmacyService,
    MedicineService,
    DermatologistService,
    ExaminationService,
    CounselingComponent,
    MedicineService,
    PatientService
  ],
  imports: [
    CommonModule
  ]
})
export class ServicesModule {
}

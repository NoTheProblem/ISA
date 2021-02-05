import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PharmacyService} from './pharmacy.service';
import {MedicineService} from './medicine.service';
import {PatientService} from './patient.service';

@NgModule({
  declarations: [],
  providers: [
    PharmacyService,
    MedicineService,
    PatientService
  ],
  imports: [
    CommonModule
  ]
})
export class ServicesModule {
}

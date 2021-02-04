import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PharmacyService} from './pharmacy.service';
import {MedicineService} from './medicine.service';

@NgModule({
  declarations: [],
  providers: [
    PharmacyService,
    MedicineService
  ],
  imports: [
    CommonModule
  ]
})
export class ServicesModule {
}

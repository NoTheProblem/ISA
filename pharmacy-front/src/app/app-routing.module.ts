import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {PharmaciesComponent} from './pharmacies/pharmacies.component';
import {MedicinesComponent} from './medicines/medicines.component';
import {ExaminationComponent} from './examination/examination.component';
import {CounselingComponent} from './counseling/counseling.component';
import {PharmacyComponent} from './pharmacy/pharmacy.component';
import {PromotionsComponent} from './promotions/promotions.component';
import {OrderFromsComponent} from './order-froms/order-froms.component';
import {PriceListComponent} from './price-list/price-list.component';
import {AbsencesComponent} from './absences/absences.component';
import {AppointmentDefComponent} from './appointment-def/appointment-def.component';
import {PharmacyMedicineComponent} from './pharmacy-medicine/pharmacy-medicine.component';
import {PharmacyEmployeesComponent} from './pharmacy-employees/pharmacy-employees.component';
import {PharmacyReportComponent} from './pharmacy-report/pharmacy-report.component';
import {PharmacyDetailsComponent} from './pharmacy-details/pharmacy-details.component';
import {OrderdetailsComponent} from './orderdetails/orderdetails.component';
import { RegisterMedicineComponent } from './register-medicine/register-medicine.component';
import { RegisterPharmacyComponent } from './register-pharmacy/register-pharmacy.component';
import {PatientlistComponent} from './patientlist/patientlist.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'pharmacies', component: PharmaciesComponent},
  {path: 'medicines', component: MedicinesComponent},
  {path: 'freeExamination', component: ExaminationComponent},
  {path: 'freeCounseling', component: CounselingComponent},
  {path: 'register-pharmacy', component: RegisterPharmacyComponent},
  {path: 'pharmacy', component: PharmacyComponent},
  {path: 'lista-korisnika', component: PatientlistComponent},
  {path: 'promotions', component: PromotionsComponent},
  {path: 'order-forms', component: OrderFromsComponent},
  {path: 'register-medicine', component: RegisterMedicineComponent},
  {path: 'price-list', component: PriceListComponent},
  {path: 'absences', component: AbsencesComponent},
  {path: 'appDef', component: AppointmentDefComponent},
  {path: 'pharmacy-medicine', component: PharmacyMedicineComponent},
  {path: 'pharmacy-employees', component: PharmacyEmployeesComponent},
  {path: 'pharmacy-report', component: PharmacyReportComponent},
  {path: 'pharmacy/:id', component: PharmacyDetailsComponent},
  {path: 'orderdetails/:id', component: OrderdetailsComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { AppRoutingModule } from './app-routing.module';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { PharmaciesComponent } from './pharmacies/pharmacies.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {ServicesModule} from './services/services.module';
import {MedicinesComponent} from './medicines/medicines.component';
import { FilterPipe } from './filter.pipe';
import {Ng2SearchPipeModule} from 'ng2-search-filter';
import { UserProfileComponent } from './user-profile/user-profile.component';
import {Ng2OrderModule} from 'ng2-order-pipe';
import { ExaminationComponent } from './examination/examination.component';
import { CounselingComponent } from './counseling/counseling.component';
import {DlDateTimeDateModule, DlDateTimePickerModule} from 'angular-bootstrap-datetimepicker';



@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    HomeComponent,
    PharmaciesComponent,
    LoginComponent,
    RegisterComponent,
    MedicinesComponent,
    UserProfileComponent,
    ExaminationComponent,
    CounselingComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ServicesModule,
    FormsModule,
    Ng2SearchPipeModule,
    Ng2OrderModule,
    DlDateTimeDateModule,  // <--- Determines the data type of the model
    DlDateTimePickerModule
  ],
  bootstrap: [AppComponent],
  providers: [FormsModule]
})
export class AppModule { }

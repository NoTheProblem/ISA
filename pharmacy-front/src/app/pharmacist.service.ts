import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {PharmacyModel} from './model/pharmacy.model';
import {Constants} from './services/constants';
import {PharmacistModel} from './model/pharmacist.model';
import {HttpClient} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class PharmacistService {
  private path: string;

  constructor(
    private httpClient: HttpClient,
    private toast: ToastrService) {
  }

  public getPharmacistForPatientByDateAndTimeAndPharmacy(pharmacy: PharmacyModel, timeInput: any, dateInput: any): Observable<Array<PharmacistModel>> {
    this.path = Constants.API + '/pharmacy/unauth/counseling/' + String(pharmacy.id) + '/' + String(timeInput) + '/' + String(dateInput);
    return this.httpClient.get<Array<PharmacistModel>>(this.path);
  }
}

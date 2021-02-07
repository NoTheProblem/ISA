import {Injectable} from '@angular/core';
import {PharmacyModel} from '../model/pharmacy.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class PharmacyService {

  constructor(private httpClient: HttpClient) {
  }

  public getAll(): Observable<Array<PharmacyModel>> {
    return this.httpClient.get<Array<PharmacyModel>>('http://localhost:8080/pharmacy/getAll');
  }

  public getPharmacyByAdmin(): Observable<PharmacyModel> {
    return this.httpClient.get<PharmacyModel>('http://localhost:8080/pharmacy/admin');
  }

}

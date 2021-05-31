import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {EmployeeModel} from '../model/employee.model';
import {DermatologistModel} from '../model/dermatologist.model';
import {PharmacistModel} from '../model/pharmacist.model';

@Injectable()
export class EmployeeService {
  path: string;
  constructor(private httpClient: HttpClient) {
  }

  public getAllDermaByPharmacyID(pharmacyID): Observable<Array<EmployeeModel>> {
    this.path = 'http://localhost:8080/pharmacy/getDerma/' + String(pharmacyID);
    return this.httpClient.get<Array<EmployeeModel>>(this.path);
  }

  public getAllPharmacistsByPharmacyID(pharmacyID): Observable<Array<EmployeeModel>> {
    this.path = 'http://localhost:8080/pharmacy/getPharma/' + String(pharmacyID);
    return this.httpClient.get<Array<EmployeeModel>>(this.path);
  }

  public getAllPharmacists(): Observable<Array<PharmacistModel>> {
    return this.httpClient.get<Array<PharmacistModel>>('http://localhost:8080/pharmacist/getAll');
  }

  public getAllDermatologists(): Observable<Array<DermatologistModel>> {
    return this.httpClient.get<Array<DermatologistModel>>('http://localhost:8080/dermatologist/getAll');
  }

}

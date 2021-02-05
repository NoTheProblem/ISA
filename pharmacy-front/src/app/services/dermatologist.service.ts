import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MedicineModel} from '../model/medicine.model';
import {DermatologistModel} from '../model/dermatologist.model';

@Injectable()
export class DermatologistService {

  constructor(private httpClient: HttpClient) {
  }

  public getAll(): Observable<Array<DermatologistModel>> {
    return this.httpClient.get<Array<DermatologistModel>>('http://localhost:8080/dermatologist/getAll');
  }

}

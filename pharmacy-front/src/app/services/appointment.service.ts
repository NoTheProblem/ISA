import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DermatologistModel} from '../model/dermatologist.model';

@Injectable()
export class AppointmentService {

  constructor(private httpClient: HttpClient) {
  }

  public getDermaForPhaAdmin(): Observable<Array<DermatologistModel>> {
    return this.httpClient.get<Array<DermatologistModel>>('http://localhost:8080/pharmacy/getDerma/phaadmin');
  }

}

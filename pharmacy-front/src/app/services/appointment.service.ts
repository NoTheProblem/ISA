import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DermatologistModel} from '../model/dermatologist.model';
import {Constants} from './constants';
import {CounselingModel} from '../model/counseling.model';


@Injectable()
export class AppointmentService {

  constructor(private httpClient: HttpClient) {
  }

  public getDermaForPhaAdmin(): Observable<Array<DermatologistModel>> {
    return this.httpClient.get<Array<DermatologistModel>>(Constants.API + '/pharmacy/getDerma/phaadmin');
  }

  public getAllScheduledAppointment(): Observable<Array<CounselingModel>> {
    return this.httpClient.get<Array<CounselingModel>>(Constants.API + '/patient/getAllScheduledAppointmentPha');
  }

  public getAllHistoryPha(): Observable<Array<CounselingModel>> {
    return this.httpClient.get<Array<CounselingModel>>(Constants.API + '/patient/getAllHistoryPha');
  }


}

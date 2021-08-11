import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ExaminationModel} from '../model/examination.model';
import {ToastrService} from 'ngx-toastr';
import {Constants} from './constants';
import {DermatologistModel} from '../model/dermatologist.model';
import {PatientModel} from '../model/patient.model';
import {CounselingModel} from '../model/counseling.model';

@Injectable()
export class CounselingService {
  private path = '';

  constructor(private httpClient: HttpClient,
              private toast: ToastrService
  ) {
  }


}

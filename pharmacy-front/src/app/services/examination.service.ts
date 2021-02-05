import {Injectable} from '@angular/core';
import {MedicineModel} from '../model/medicine.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ExaminationModel} from '../model/examination.model';

@Injectable()
export class ExaminationService {

  constructor(private httpClient: HttpClient) {
  }

  public getAllFree(): Observable<Array<ExaminationModel>> {
    return this.httpClient.get<Array<ExaminationModel>>('http://localhost:8080/examination/getAllFree');
  }

}

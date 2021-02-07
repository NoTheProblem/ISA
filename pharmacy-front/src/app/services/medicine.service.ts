import {Injectable} from '@angular/core';
import {MedicineModel} from '../model/medicine.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PromotionModel} from '../model/promotion.model';

@Injectable()
export class MedicineService {

  constructor(private httpClient: HttpClient) {
  }

  public getAll(): Observable<Array<MedicineModel>> {
    return this.httpClient.get<Array<MedicineModel>>('http://localhost:8080/medicine/getAll');
  }

}

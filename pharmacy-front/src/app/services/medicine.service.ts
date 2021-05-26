import {Injectable} from '@angular/core';
import {MedicineModel} from '../model/medicine.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PriceMedicineModel} from '../model/priceMedicine.model';
import {ToastrService} from 'ngx-toastr';

@Injectable()
export class MedicineService {
  private path = '';

  constructor(private httpClient: HttpClient,
              private toast: ToastrService) {
  }

  public getAll(): Observable<Array<MedicineModel>> {
    return this.httpClient.get<Array<MedicineModel>>('http://localhost:8080/medicine/getAll');
  }

  public getMedicinesForPhaAdmin(): Observable<Array<MedicineModel>> {
    return this.httpClient.get<Array<MedicineModel>>('http://localhost:8080/medicine/getMedicinesForPhaAdmin');
  }

  public getMedPriceForPhaAdmin(id: number): Observable<PriceMedicineModel> {
    this.path = 'http://localhost:8080/medicine/getMedPriceForPhaAdmin/' + String(id);
    return this.httpClient.get<PriceMedicineModel>(this.path);
  }

  public addNewMedPrice(priceMedicineModel: PriceMedicineModel): void {
    this.httpClient.post('http://localhost:8080/medicine/addNewMedPrice', priceMedicineModel).subscribe(
      (response: any) => {
        this.toast.success(`Cena je dodata.`);
      },
      (error => {
        this.toast.error(`Cena nije dodata.`);
      })
    );
  }

}

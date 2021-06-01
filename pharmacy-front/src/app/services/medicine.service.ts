import {Injectable} from '@angular/core';
import {MedicineModel} from '../model/medicine.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PriceMedicineModel} from '../model/priceMedicine.model';
import {PromotionModel} from '../model/promotion.model';
import {MedicineRegisterModel} from '../model/medicineRegister.model';
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

  public getAllReg(): Observable<Array<MedicineRegisterModel>> {
    return this.httpClient.get<Array<MedicineRegisterModel>>('http://localhost:8080/medicine/getAllReg');
  }


  public addMedicine(medicine: MedicineRegisterModel): void {
    this.httpClient.post('http://localhost:8080/medicine/addMedicine', medicine).subscribe(
      (response: any) => {
        this.toast.success(`${medicine.type} je dodat.`);
      },
      (error => {
        this.toast.error(`${medicine.type} nije dodat.`);
      })
    );
  }

  public removeMedicineFromPhamracy(medicine: MedicineModel): void {
    this.path = 'http://localhost:8080/medicine/removeMedicineFromPhamracy';
    this.httpClient.post(this.path, medicine).subscribe(
      (response: any) => {
        this.toast.success(`Lek je uklonjen.`);
      },
      (error => {
        this.toast.error(`Lek nije uspesno uklonjen.`);
      })
    );
  }

  public getMissingMedicines(): Observable<Array<MedicineModel>> {
    return this.httpClient.get<Array<MedicineModel>>('http://localhost:8080/medicine/getMissingMedicines');
  }

}

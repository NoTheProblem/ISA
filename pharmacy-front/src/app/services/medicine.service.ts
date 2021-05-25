import {Injectable} from '@angular/core';
import {MedicineModel} from '../model/medicine.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PromotionModel} from '../model/promotion.model';
import {MedicineRegisterModel} from '../model/medicineRegister.model';
import {ToastrService} from 'ngx-toastr';

@Injectable()
export class MedicineService {

  constructor(private httpClient: HttpClient,
              private toast: ToastrService) {
  }

  public getAll(): Observable<Array<MedicineModel>> {
    return this.httpClient.get<Array<MedicineModel>>('http://localhost:8080/medicine/getAll');
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
}

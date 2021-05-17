import {Injectable} from '@angular/core';
import {PharmacyModel} from '../model/pharmacy.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PromotionModel} from '../model/promotion.model';
import * as path from 'path';
import {ToastrService} from 'ngx-toastr';

@Injectable()
export class PharmacyService {
  private path: string;

  constructor(
    private httpClient: HttpClient,
    private toast: ToastrService) {
  }

  public getAll(): Observable<Array<PharmacyModel>> {
    return this.httpClient.get<Array<PharmacyModel>>('http://localhost:8080/pharmacy/getAll');
  }

  public getPharmacyByAdmin(): Observable<PharmacyModel> {
    return this.httpClient.get<PharmacyModel>('http://localhost:8080/pharmacy/admin');
  }

  public getPharmacyByID(pharmacyID: number): Observable<PharmacyModel> {
    this.path = 'http://localhost:8080/pharmacy/unauth/' + String(pharmacyID);
    return this.httpClient.get<PharmacyModel>(this.path);
  }

  public subscribeToPromotions(pharmacyID): void {
    this.httpClient.get(`http://localhost:8080/pharmacy/subscription/${pharmacyID}`).subscribe(
      (response: any) => {
        this.toast.success(`Uspesno ste se pretplatili!`);
      },
      (error => {
        this.toast.error(`Pretplata nije uspela!`);
      })
    );
  }
}


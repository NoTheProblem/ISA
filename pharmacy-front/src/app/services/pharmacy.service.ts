import {Injectable} from '@angular/core';
import {PharmacyModel} from '../model/pharmacy.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ToastrService} from 'ngx-toastr';
import {MedicineRegisterModel} from '../model/medicineRegister.model';
import {MedicineModel} from '../model/medicine.model';
import {ExaminationModel} from '../model/examination.model';

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
    this.path = 'http://localhost:8080/pharmacy/unauth/pharmacy/' + String(pharmacyID);
    return this.httpClient.get<PharmacyModel>(this.path);
  }

  public addPharmacy(pharmacy: PharmacyModel): void {
    this.httpClient.post('http://localhost:8080/pharmacy/addPharmacy', pharmacy).subscribe(
      (response: any) => {
        this.toast.success(`${pharmacy.name} je dodat.`);
      },
      (error => {
        this.toast.error(`${pharmacy.name} nije dodat.`);
      })
    );
  }

  public subscribeToPromotions(pharmacyID): void {
    this.httpClient.get(`http://localhost:8080/pharmacy/subscribe/${pharmacyID}`).subscribe(
      (response: any) => {
        this.toast.success(`Uspesno ste se pretplatili!`);
      },
      (error => {
        this.toast.error(`Pretplata nije uspela!`);
      })
    );
  }
  public updatePharmacyInfo(pharmacy: PharmacyModel): void {
    this.httpClient.post('http://localhost:8080/pharmacy/updatePharmacyInfo', pharmacy).subscribe(
      (response: any) => {
        this.toast.success(`Azuriranje je uspelo.`);
      },
      (error => {
        this.toast.error(`Azuriranje nije uspelo`);
      })
    );
  }

  public getAvailableMedicines(pharmacyID: number): Observable<Array<MedicineModel>> {
    this.path = 'http://localhost:8080/pharmacy/unauth/medicines/' + String(pharmacyID);
    return this.httpClient.get<Array<MedicineModel>>(this.path);
  }

  public getAvailableExaminations(pharmacyID: number): Observable<Array<ExaminationModel>> {
    this.path = 'http://localhost:8080/pharmacy/unauth/examinations/' + String(pharmacyID);
    return this.httpClient.get<Array<ExaminationModel>>(this.path);
  }
}


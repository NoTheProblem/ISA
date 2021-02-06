import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MedicineModel} from '../model/medicine.model';
import {Toast, ToastrService} from 'ngx-toastr';

@Injectable()
export class PatientService {

  constructor(
    private httpClient: HttpClient,
    private toast: ToastrService
  ) {
  }

  public addAllergy(medicine: MedicineModel): void {
    this.httpClient.post('http://localhost:8080/patient/addAllergy', medicine).subscribe(
      (response: any) => {
        this.toast.success(`Dodata alergija na ${medicine.name}`);
      },
      (error => {
        this.toast.error(`Nije dodata alergija na ${medicine.name}`);
      })
    );
  }

}

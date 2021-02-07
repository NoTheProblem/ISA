import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AbsenceModel} from '../model/absence.model';
import {Observable} from 'rxjs';
import {Toast, ToastrService} from 'ngx-toastr';

@Injectable()
export class AbsenceService {

  constructor(private httpClient: HttpClient,
              private toast: ToastrService
              ) {
  }


  public getAllDermatologistRequests(): Observable<Array<AbsenceModel>> {
    return this.httpClient.get<Array<AbsenceModel>>('http://localhost:8080/absence/getAllDermatologistRequests');
  }

  public getAllPharmacistRequests(): Observable<Array<AbsenceModel>> {
    return this.httpClient.get<Array<AbsenceModel>>('http://localhost:8080/absence/getAllPharmacistRequests');
  }

  public acceptAbsencePha(absence: AbsenceModel): void {
    this.httpClient.post('http://localhost:8080/absence/acceptPha', absence).subscribe(
      (response: any) => {
        this.toast.success(`${absence.typeOfAbsence} je odobren.`);
      },
      (error => {
        this.toast.error(`Greske pri odobravanju ${absence.typeOfAbsence}`);
      })
    );
  }

  public declineAbsencePha(absence: AbsenceModel): void {
    this.httpClient.post('http://localhost:8080/absence/declinePha', absence).subscribe(
      (response: any) => {
        this.toast.success(`${absence.typeOfAbsence} je odbijen.`);
      },
      (error => {
        this.toast.error(`Greske pri odbijanju ${absence.typeOfAbsence}`);
      })
    );
  }

}

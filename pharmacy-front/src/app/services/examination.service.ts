import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ExaminationModel} from '../model/examination.model';
import {ToastrService} from 'ngx-toastr';

@Injectable()
export class ExaminationService {

  constructor(private httpClient: HttpClient,
              private toast: ToastrService
  ) {
  }

  public getAllFree(): Observable<Array<ExaminationModel>> {
    return this.httpClient.get<Array<ExaminationModel>>('http://localhost:8080/examination/getAllFree');
  }

  public addExamination(examination: ExaminationModel): void {
    this.httpClient.post('http://localhost:8080/examination/addExamination', examination).subscribe(
      (response: any) => {
        this.toast.success(`Termin je uspesno dodat!`);
      },
      (error => {
        this.toast.error(`Doslo je do greske`);
      })
    );
  }

}

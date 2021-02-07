import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PromotionModel} from '../model/promotion.model';
import {ToastrService} from 'ngx-toastr';

@Injectable()
export class PromotionService {

  constructor(private httpClient: HttpClient,
              private toast: ToastrService) {
  }

  public getAllActive(): Observable<Array<PromotionModel>> {
    return this.httpClient.get<Array<PromotionModel>>('http://localhost:8080/promotion/getAllActive');
  }

  public getAll(): Observable<Array<PromotionModel>> {
    return this.httpClient.get<Array<PromotionModel>>('http://localhost:8080/promotion/getAll');
  }

  public addPromotion(promotion: PromotionModel): void {
    this.httpClient.post('http://localhost:8080/promotion/addPromotion', promotion).subscribe(
      (response: any) => {
        this.toast.success(`${promotion.type} je dodata.`);
      },
      (error => {
        this.toast.error(`${promotion.type} nije dodata.`);
      })
    );
  }

}

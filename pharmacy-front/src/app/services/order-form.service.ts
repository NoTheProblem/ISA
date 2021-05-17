import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {OrderFormModel} from '../model/order-form.model';
import {ToastrService} from 'ngx-toastr';

@Injectable()
export class OrderFormService {

  constructor(private httpClient: HttpClient,
              private toast: ToastrService) {
  }

  // tslint:disable-next-line:typedef
  makeNewOrder(order: OrderFormModel) {

  }
}

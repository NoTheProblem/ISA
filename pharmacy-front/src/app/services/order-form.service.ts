import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {OrderFormModel} from '../model/order-form.model';
import {ToastrService} from 'ngx-toastr';
import {MedicineModel} from '../model/medicine.model';

@Injectable()
export class OrderFormService {

  constructor(private httpClient: HttpClient,
              private toast: ToastrService) {
  }

  public addOrderForm(orderFormModel: OrderFormModel): void {
    this.httpClient.post('http://localhost:8080/order/addPurchaseOrder', orderFormModel).subscribe(
      (response: any) => {
        this.toast.success(`Dodata alergija na `);
      },
      (error => {
        this.toast.error(`Nije dodata alergija na `);
      })
    );
  }

}

import {MedicineQuantityModel} from './medicineQuantity.model';

export class OrderFormModel {
  constructor(
    public id: number = 0,
    public medicineQuantityID: number,
    public status: string,
    public adminID: number,
    public supplierID: number,
    public price: number,
    public endDate: Date
  ) {
  }
}

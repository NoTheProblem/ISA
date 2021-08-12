import {PharmacyModel} from './pharmacy.model';
import {MedicineModel} from './medicine.model';
import {PatientModel} from './patient.model';

export class ReservationModel {
  constructor(
    public medicineid: number = 0,
    public pharmacyid: number = 0,
    public pickedUp: boolean = false,
    public pickedUpDate: Date = new Date(),
    public pickedUpTime: string = '',
    public endDate: Date = new Date(),
    public endTime: string = '',
    public id: number = 0,
    public medicineName: string = '',
    public pharmacyName: string = ''
  ) {
  }
}

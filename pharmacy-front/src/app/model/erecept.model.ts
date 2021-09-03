import {MedicineModel} from './medicine.model';
import {PharmacyModel} from './pharmacy.model';
import {PatientModel} from './patient.model';

export class EreceptModel {
  constructor(
    public id: number = 0,
    public code: string = '',
    public price: number = 0,
    public status: string = '',
    public dateOfIssue: Date = new Date(),
    public medicine: string = '',
    public pharmacyDto: PharmacyModel = new PharmacyModel()

  ) {
  }
}



import {MedicineModel} from './medicine.model';
import {PharmacyModel} from './pharmacy.model';
import {PatientModel} from './patient.model';

export class EmployeeModel {
  constructor(
    public id: number = 0,
    public code: string = '',
    public status: string = '',
    public dateOfIssue: Date = new Date(),
    public medicineDto: MedicineModel = new MedicineModel(),
    public pharmacyDto: PharmacyModel = new PharmacyModel()

  ) {
  }
}



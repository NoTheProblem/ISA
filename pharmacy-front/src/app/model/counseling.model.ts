import {PharmacyModel} from './pharmacy.model';
import {PharmacistModel} from './pharmacist.model';

export class CounselingModel {
  constructor(
    public penalty: boolean = false,
    public free: boolean = false,
    public counselingReport: string = '',
    public loyaltyScore: number = 0,
    public date: Date = new Date(),
    public time: string = '',
    public durationMinutes: number = 0,
    public price: number = 0,
    public pharmacistId: number= 0,
    public patient: number= 0,
    public pharmacistName: string = '',
    public pharmacistLastname: string = '',
    public pharmacistEvaluationGrade: string = '',
    public pharmacy: PharmacyModel = new PharmacyModel()

  ) {
  }
}

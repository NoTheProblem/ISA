import {Time} from '@angular/common';
import {PharmacyModel} from './pharmacy.model';
import {DermatologistModel} from './dermatologist.model';

export class WorkingHoursModel {
  constructor(
    public id: number = 0,
    public startTime: Time,
    public endTime: Time,
    public pharmacy: PharmacyModel,
    public dermatologist: DermatologistModel
  ) {
  }
}


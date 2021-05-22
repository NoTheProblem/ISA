import { Component, OnInit } from '@angular/core';
import {DermatologistModel} from '../model/dermatologist.model';
import {AppointmentService} from '../services/appointment.service';
import {ExaminationModel} from '../model/examination.model';
import {WorkingHoursModel} from '../model/workingHours.model';
import {TokenStorageService} from '../_services/token-storage.service';

@Component({
  selector: 'app-appointment-def',
  templateUrl: './appointment-def.component.html',
  styleUrls: ['./appointment-def.component.css']
})
export class AppointmentDefComponent implements OnInit {
  public dermatoligists: Array<DermatologistModel>;
  public derma: DermatologistModel;
  private appointment: ExaminationModel;
  private workingHours: WorkingHoursModel;

  show = true;

  constructor(private appointmentService: AppointmentService,
              private tokenStorageService: TokenStorageService
  ) { }

  ngOnInit(): void {
    this.appointmentService.getDermaForPhaAdmin().subscribe((dermatoligists: Array<DermatologistModel>) => {
      this.dermatoligists = dermatoligists;
    });
  }

  call(): void {
    return;
  }

  openDerma(der: DermatologistModel): void {
    this.show = false;
    this.derma = der;
    console.log(der);
    console.log(der.pharmacys);
    console.log(der.workingHours);
    this.workingHours = this.getMyWorkingHours(der.workingHours);
    console.log(this.workingHours);
    return;
  }

  getMyWorkingHours(workingHoursModels: Array<WorkingHoursModel>): WorkingHoursModel{
    return workingHoursModels.pop();
  }
}

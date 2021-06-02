import { Component, OnInit } from '@angular/core';
import {DermatologistModel} from '../model/dermatologist.model';
import {AppointmentService} from '../services/appointment.service';
import {ExaminationModel} from '../model/examination.model';
import {WorkingHoursModel} from '../model/workingHours.model';
import {TokenStorageService} from '../_services/token-storage.service';
import {ExaminationService} from '../services/examination.service';


@Component({
  selector: 'app-appointment-def',
  templateUrl: './appointment-def.component.html',
  styleUrls: ['./appointment-def.component.css']
})
export class AppointmentDefComponent implements OnInit {
  private dermatoligists: Array<DermatologistModel>;
  private derma: DermatologistModel;
  private workingHours: WorkingHoursModel;
  private examinations: Array<ExaminationModel> = new Array<ExaminationModel>();
  private examinationsBackUp: Array<ExaminationModel> = new Array<ExaminationModel>();
  private examination: ExaminationModel;
  private examinationDateInput: Date;

  show = true;
  showAppointments = false;
  showMake = false;
  form: any = {};
  private x: Date;
  private y: Date;


  constructor(private appointmentService: AppointmentService,
              private tokenStorageService: TokenStorageService,
              private examinationService: ExaminationService
  ) { }

  ngOnInit(): void {
    this.appointmentService.getDermaForPhaAdmin().subscribe((dermatoligists: Array<DermatologistModel>) => {
      this.dermatoligists = dermatoligists;
    });
  }

  openDerma(der: DermatologistModel): void {
    this.show = false;
    this.derma = der;
    this.workingHours = this.getMyWorkingHours(der.workingHours);
    this.examinations = der.examinations;
    this.examinationsBackUp = der.examinations;
    return;
  }

  getMyWorkingHours(workingHoursModels: Array<WorkingHoursModel>): WorkingHoursModel{
    return workingHoursModels.pop();
  }

  showExaminations(form): void {
    this.examinations = this.examinationsBackUp;
    this.showAppointments = true;
    this.y = new Date(form.examinationDateInput);
    for (const examination of this.examinations){
      this.x = new Date(examination.date);
      if (this.x.getDate() !== this.y.getDate() || this.x.getMonth() !== this.y.getMonth()){
        // @ts-ignore
        this.examinations.pop(examination);
      }
    }
    return;
  }

  createAppointment(form: any): void {
    this.examination = new ExaminationModel(0, false, false, '', 0, undefined, undefined, 0, 0, 0, 0, '', '', '', undefined, undefined);
    this.examination.date = new Date();
    this.examination.date = form.examinationDateInput;
    console.log(form.examinationDateInput)  ;
    this.examination.durationMinutes = form.duration;
    this.examination.price = form.price;
    this.examination.time = form.startTime;
    this.examination.dermatologistId = this.derma.id;
    this.examinationService.addExamination(this.examination);
  }
}


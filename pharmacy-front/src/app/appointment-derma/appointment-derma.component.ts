import { Component, OnInit } from '@angular/core';
import {ExaminationService} from '../services/examination.service';
import {PatientService} from '../services/patient.service';
import {ExaminationModel} from '../model/examination.model';
import {Constants} from '../services/constants';
import {HttpClient} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-appointment-derma',
  templateUrl: './appointment-derma.component.html',
  styleUrls: ['./appointment-derma.component.css']
})
export class AppointmentDermaComponent implements OnInit {

  constructor(private examinationService: ExaminationService, private patientService: PatientService, private httpClient: HttpClient,
              private toast: ToastrService) { }
  public examinations: Array<ExaminationModel>;

  ngOnInit(): void {
    this.examinationService.getAllScheduledAppointment().subscribe((examinationList: Array<ExaminationModel>) => {
      this.examinations = examinationList;
    });
  }

  change(medicine): any {
    let date = medicine;
    date = Number(date);
    const d = new Date(date);
    const ds = d.toLocaleDateString();
    console.log(ds);
    return ds;
  }

  public cancelAppointment(examination: ExaminationModel): void {
    this.patientService.cancelExamination(examination);

  }




}







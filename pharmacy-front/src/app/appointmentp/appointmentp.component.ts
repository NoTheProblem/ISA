import { Component, OnInit } from '@angular/core';
import {CounselingModel} from '../model/counseling.model';
import {CounselingService} from '../services/counseling.service';
import {PatientService} from '../services/patient.service';
import {HttpClient} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {AppointmentService} from '../services/appointment.service';

@Component({
  selector: 'app-appointmentp',
  templateUrl: './appointmentp.component.html',
  styleUrls: ['./appointmentp.component.css']
})
export class AppointmentpComponent implements OnInit {

  constructor(private appointmentService: AppointmentService, private patientService: PatientService, private httpClient: HttpClient,
              private toast: ToastrService) { }
  public counselings: Array<CounselingModel>;

  public  currentDate = new Date();

  ngOnInit(): void {
    this.appointmentService.getAllScheduledAppointment().subscribe((counselingsList: Array<CounselingModel>) => {
      this.counselings = counselingsList;
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

  public cancelAppointment(counseling: CounselingModel): void {
    this.patientService.cancelCounseling(counseling);
  }
}

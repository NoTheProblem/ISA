import { Component, OnInit } from '@angular/core';
import {ExaminationModel} from '../model/examination.model';
import {ExaminationService} from '../services/examination.service';
import {PatientService} from '../services/patient.service';
import {CounselingModel} from '../model/counseling.model';
import {CounselingService} from '../services/counseling.service';
import {AppointmentDefComponent} from '../appointment-def/appointment-def.component';
import {AppointmentService} from '../services/appointment.service';
import {HttpClient} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-history-pharmacist',
  templateUrl: './history-pharmacist.component.html',
  styleUrls: ['./history-pharmacist.component.css']
})
export class HistoryPharmacistComponent implements OnInit {

  public name: string;
  public key: any;
  public reverse: boolean;
  public examine: any;

  constructor(private appointmentService: AppointmentService, private patientService: PatientService, private httpClient: HttpClient,
              private toast: ToastrService) { }
  public counselings: Array<CounselingModel>;

  public  currentDate = new Date();

  ngOnInit(): void {
    this.appointmentService.getAllHistoryPha().subscribe((counselingsList: Array<CounselingModel>) => {
      this.counselings = counselingsList;
    });

  }


  sort(key): void {
    this.key = key;
    this.reverse = !this.reverse;
  }

  change(medicine): any {
    let date = medicine;
    date = Number(date);
    const d = new Date(date);
    const ds = d.toLocaleDateString();
    console.log(ds);
    return ds;
  }
}

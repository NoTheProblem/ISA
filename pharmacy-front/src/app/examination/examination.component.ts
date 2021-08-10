import { Component, OnInit } from '@angular/core';
import {ExaminationService} from '../services/examination.service';
import {ExaminationModel} from '../model/examination.model';
import {MedicineModel} from '../model/medicine.model';
import {PatientService} from '../services/patient.service';

@Component({
  selector: 'app-examination',
  templateUrl: './examination.component.html',
  styleUrls: ['./examination.component.css']
})
export class ExaminationComponent implements OnInit {

  public examinations: Array<ExaminationModel>;


  public name: string;
  public key: any;
  public reverse: boolean;
  public examine: any;

  constructor( private examinationService: ExaminationService, private patientService: PatientService
  ) {
  }

  ngOnInit(): void {
      this.examinationService.getAllFree().subscribe((examinationList: Array<ExaminationModel>) => {
        this.examinations = examinationList;
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

  public reservedAppointment(examination: ExaminationModel): void {
    this.patientService.addExamination(examination);
  }



}

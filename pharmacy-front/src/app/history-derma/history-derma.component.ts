import { Component, OnInit } from '@angular/core';
import {ExaminationModel} from '../model/examination.model';
import {ExaminationService} from '../services/examination.service';
import {PatientService} from '../services/patient.service';
import {DermatologistModel} from '../model/dermatologist.model';
import {PharmacistModel} from '../model/pharmacist.model';
import {PharmacyModel} from '../model/pharmacy.model';

@Component({
  selector: 'app-history-derma',
  templateUrl: './history-derma.component.html',
  styleUrls: ['./history-derma.component.css']
})
export class HistoryDermaComponent implements OnInit {

  public examinations: Array<ExaminationModel>;
  public choosenDerma: DermatologistModel;
  public dermaId: number;
  public showInput: boolean;



  public name: string;
  public key: any;
  public reverse: boolean;
  public examine: any;
  public grade: any;

  constructor( private examinationService: ExaminationService, private patientService: PatientService
  ) {
  }

  ngOnInit(): void {
    this.examinationService.getAllHistoryDerma().subscribe((examinationList: Array<ExaminationModel>) => {
      this.examinations = examinationList;
      this.showInput  = false;
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

  public evaluate(examination: ExaminationModel): void {
    this.dermaId = examination.dermatologistId;
    this.showInput  = true;


  }




}

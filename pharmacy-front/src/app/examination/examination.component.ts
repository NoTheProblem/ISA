import { Component, OnInit } from '@angular/core';
import {MedicineService} from '../services/medicine.service';
import {ExaminationService} from '../services/examination.service';
import {MedicineModel} from '../model/medicine.model';
import {ExaminationModel} from '../model/examination.model';
import {DermatologistService} from '../services/dermatologist.service';
import {DermatologistModel} from '../model/dermatologist.model';

@Component({
  selector: 'app-examination',
  templateUrl: './examination.component.html',
  styleUrls: ['./examination.component.css']
})
export class ExaminationComponent implements OnInit {

  public examinations: Array<ExaminationModel>;
  public dermatologists: Array<DermatologistModel>;

  public name: string;
  public key: any;
  public reverse: boolean;

  constructor( private examinationService: ExaminationService, private dermatologistService: DermatologistService
  ) {
  }



  ngOnInit(): void {
      this.examinationService.getAllFree().subscribe((examinationList: Array<ExaminationModel>) => {
        this.examinations = examinationList;
    });
      this.dermatologistService.getAll().subscribe((dermatologistList: Array<DermatologistModel>) => {
      this.dermatologists = dermatologistList;
    });

      for (const examine of this.examinations) {

    }

  }

  sort(key): void {
    this.key = key;
    this.reverse = !this.reverse;
  }



}

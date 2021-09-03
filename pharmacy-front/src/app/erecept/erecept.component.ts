import { Component, OnInit } from '@angular/core';
import {ExaminationModel} from '../model/examination.model';
import {ExaminationService} from '../services/examination.service';
import {PatientService} from '../services/patient.service';
import {EreceptModel} from '../model/erecept.model';

@Component({
  selector: 'app-erecept',
  templateUrl: './erecept.component.html',
  styleUrls: ['./erecept.component.css']
})
export class EReceptComponent implements OnInit {

  public erecepts: Array<EreceptModel>;


  public name: string;
  public key: any;
  public reverse: boolean;
  public examine: any;
  private code: string;
  public status: string;

  constructor(  private patientService: PatientService
  ) {
  }

  ngOnInit(): void {
    this.patientService.getAllEReceptForPatient().subscribe((ereceptList: Array<EreceptModel>) => {
      this.erecepts = ereceptList;
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

  public search(): void {
    if (this.status === '') {
      this.erecepts;
    } else {
      this.erecepts = this.erecepts.filter(res => {
        return (
          res.status.toLocaleLowerCase().match(this.status.toLocaleLowerCase())

        );

      });

    }
  }



}

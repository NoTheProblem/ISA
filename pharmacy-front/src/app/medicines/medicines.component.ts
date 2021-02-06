import {Component, OnInit, Pipe, PipeTransform} from '@angular/core';
import {MedicineService} from '../services/medicine.service';
import {MedicineModel} from '../model/medicine.model';
import {PharmacyModel} from '../model/pharmacy.model';
import {PharmacyService} from '../services/pharmacy.service';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-medicines',
  templateUrl: './medicines.component.html',
  styleUrls: ['./medicines.component.css']
})
export class MedicinesComponent implements OnInit {

  public medicines: Array<MedicineModel>;
  searchTerm: string;
  term: string;
  reverse = false;
  name = '';
  key = '';


  constructor(
    private medicineService: MedicineService
  ) {
  }

  ngOnInit(): void {
      this.medicineService.getAll().subscribe((medicineList: Array<MedicineModel>) => {

      this.medicines = medicineList;
    });
  }

  sort(key): void {
    this.key = key;
    this.reverse = !this.reverse;
  }

  search(){
    if (this.name === ''){
      this.ngOnInit();
    }
    else{
      this.medicines = this.medicines.filter(res => {
        return (
          res.name.toLocaleLowerCase().match(this.name.toLocaleLowerCase()) ||
          res.code.toLocaleLowerCase().match(this.name.toLocaleLowerCase()) ||
          res.type.toLocaleLowerCase().match(this.name.toLocaleLowerCase()) ||
          res.shape.toLocaleLowerCase().match(this.name.toLocaleLowerCase()) ||
          res.manufacturer.toLocaleLowerCase().match(this.name.toLocaleLowerCase()) ||
          res.composition.toLocaleLowerCase().match(this.name.toLocaleLowerCase())
        );

      });

    }
  }

}




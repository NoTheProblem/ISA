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


  constructor(
    private medicineService: MedicineService
  ) {
  }

  ngOnInit(): void {
    this.medicineService.getAll().subscribe((medicineList: Array<MedicineModel>) => {

      this.medicines = medicineList;
    });
  }

}




import { Component, OnInit } from '@angular/core';
import {MedicineService} from '../services/medicine.service';
import {MedicineModel} from '../model/medicine.model';
import {PharmacyModel} from '../model/pharmacy.model';
import {PharmacyService} from '../services/pharmacy.service';

@Component({
  selector: 'app-medicines',
  templateUrl: './medicines.component.html',
  styleUrls: ['./medicines.component.css']
})
export class MedicinesComponent implements OnInit {

  public medicines: Array<MedicineModel>;

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

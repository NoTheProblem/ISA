import {Component, OnInit} from '@angular/core';
import {MedicineService} from '../services/medicine.service';
import {MedicineModel} from '../model/medicine.model';
import {PatientService} from '../services/patient.service';
import {TokenStorageService} from '../_services/token-storage.service';
import {ReservationModel} from '../model/reservation.model';

@Component({
  selector: 'app-medicines',
  templateUrl: './medicines.component.html',
  styleUrls: ['./medicines.component.css']
})
export class MedicinesComponent implements OnInit {


  constructor(
    private medicineService: MedicineService,
    private tokenStorageService: TokenStorageService,
    private patientService: PatientService
  ) {
  }

  public medicines: Array<MedicineModel>;
  public medicine: MedicineModel;
  public reservation: ReservationModel;
  public end: Date = new Date();
  searchTerm: string;
  term: string;
  reverse = false;
  name = '';
  key = '';
  isLoggedIn = false;
  price = 1;
  selectedDate: any;
  flag: boolean;


  ngOnInit(): void {
    this.initMedicines();
  }

  public addAllergy(medicine: MedicineModel): void {
    this.patientService.addAllergy(medicine);
  }

  public addReservation(medicine: MedicineModel): void {
    this.flag = !this.flag;
    this.medicine = medicine;
    console.log(this.reservation);
  }

  public activateButton(date: any): void {
    console.log(this.reservation);
    this.reservation = new ReservationModel(false, this.selectedDate, 1, 1, this.medicine.id);
    this.flag = !this.flag;
    this.patientService.addReservation(this.reservation);
  }
  public sort(key): void {
    this.key = key;
    this.reverse = !this.reverse;
  }

  public search(): void {
    if (this.name === '') {
      this.initMedicines();
    } else {
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

  private initMedicines(): void {
    this.medicineService.getAll()
      .subscribe((medicineList: Array<MedicineModel>) => {
        this.medicines = medicineList;
      });
    this.isLoggedIn = !!this.tokenStorageService.getToken();
  }

}




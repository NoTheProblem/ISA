import { Component, OnInit } from '@angular/core';
import {MedicineModel} from '../model/medicine.model';
import {MedicineService} from '../services/medicine.service';
import {TokenStorageService} from '../_services/token-storage.service';
import {PatientService} from '../services/patient.service';
import {PharmacyModel} from '../model/pharmacy.model';
import {PharmacyService} from '../services/pharmacy.service';
import {ReservationModel} from '../model/reservation.model';
import {CounselingModel} from '../model/counseling.model';

@Component({
  selector: 'app-available-medicines',
  templateUrl: './available-medicines.component.html',
  styleUrls: ['./available-medicines.component.css']
})
export class AvailableMedicinesComponent implements OnInit {

  public medicines: Array<MedicineModel>;
  public pharmacies: Array<PharmacyModel>;
  public  choosenMedicine: MedicineModel;
  public choosenPharmacy: PharmacyModel;


  searchTerm: string;
  term: string;
  reverse = false;
  name = '';
  key = '';
  isLoggedIn = false;
  showPharmacy = false;
  medicineId: number;
  pharmacyId: number;
  dateTime = false;
  dateInput: any;
  timeInput: any;
  reservation: ReservationModel = new ReservationModel();


  constructor(
    private medicineService: MedicineService,
    private pharmacyService: PharmacyService,
    private tokenStorageService: TokenStorageService,
    private patientService: PatientService
  ) {
  }

  ngOnInit(): void {
    this.initMedicines();
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
    this.medicineService.getAllAvailableMedicines()
      .subscribe((medicineList: Array<MedicineModel>) => {
        this.medicines = medicineList;
      });
    this.isLoggedIn = !!this.tokenStorageService.getToken();
  }

  public showPharmacies(id, medicine): void {
    this.showPharmacy = true;
    this.medicineId = id;
    this.choosenMedicine = medicine;
    this.pharmacyService.getAllPharmacyForMedicine(id)
      .subscribe((pharmacyList: Array<PharmacyModel>) => {
        this.pharmacies = pharmacyList;
      });

  }

  public showDateTime(id, pharmacy): void {
    this.pharmacyId = id;
    this.dateTime = true;
    this.choosenPharmacy = pharmacy;
  }

  public reserveMedicine(): void {
    /*this.reservation.pharmacy = this.choosenPharmacy;
    console.log(this.choosenPharmacy.name);
    this.reservation.medicine = this.choosenMedicine;
    console.log(this.choosenMedicine.name);
    this.reservation.endDate = this.dateInput;
    console.log(this.dateInput);
    this.reservation.endTime = this.timeInput;
    console.log(this.timeInput);*/
    /*this.reservation.pharmacyId = this.pharmacyId;
    console.log(this.reservation.pharmacyId);
    this.reservation.medicineId = 1;
    console.log(this.reservation.medicineId);*/
    this.reservation.medicineid = this.medicineId;
    this.reservation.pharmacyid = this.pharmacyId;
    this.reservation.endTime = this.timeInput.toString();
    this.reservation.endDate = this.dateInput;
    this.patientService.reserveMedicine(this.reservation);
  }
}

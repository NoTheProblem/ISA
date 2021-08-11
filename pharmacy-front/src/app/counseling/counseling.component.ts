import { Component, OnInit } from '@angular/core';
import {ExaminationModel} from '../model/examination.model';
import {PharmacyModel} from '../model/pharmacy.model';
import {AppointmentService} from '../services/appointment.service';
import {PharmacyService} from '../services/pharmacy.service';
import {PharmacistModel} from '../model/pharmacist.model';
import {TokenStorageService} from '../_services/token-storage.service';
import {PharmacistService} from '../pharmacist.service';
import {CounselingModel} from '../model/counseling.model';
import {PatientService} from '../services/patient.service';
import {BidModel} from '../model/bid.model';

@Component({
  selector: 'app-counseling',
  templateUrl: './counseling.component.html',
  styleUrls: ['./counseling.component.css']
})
export class CounselingComponent implements OnInit {
  public key: any;

  constructor(private pharmacyService: PharmacyService, private pharmacistService: PharmacistService, private patientService: PatientService) { }

  selectedDate: string;
  pharmacies: Array<PharmacyModel> = new Array<PharmacyModel>();
  pharmacyChoosen: PharmacyModel = new PharmacyModel();
  pharmacists: Array<PharmacistModel> = new Array<PharmacistModel>();
  counseling: CounselingModel = new CounselingModel();

  form: any;
  timeInput: any;
  dateInput: any;
  reverse = false;
  showPharmacies = false;
  showPharmacist = false;
  showTimeDate  = true;

  ngOnInit(): void {
  }

  showPharmacy(timeInput, dateInput): void {
    console.log(timeInput);
    console.log(dateInput);
    this.showPharmacies = true;
    this.showTimeDate  = false;
    this.pharmacyService.getPharmacyForPatientByDateAndTime(timeInput, dateInput).subscribe((pharmacies: Array<PharmacyModel>) => {
      this.pharmacies = pharmacies;
    });

  }

  public sort(key): void {
    this.key = key;
    this.reverse = !this.reverse;
  }

  showPharmacists(pharmacy, dateInput, timeInput): void {
    this.showTimeDate = false;
    this.showPharmacies = false;
    this.showPharmacist = true;
    this.pharmacyChoosen = pharmacy;
    this.pharmacistService.getPharmacistForPatientByDateAndTimeAndPharmacy(pharmacy, dateInput, timeInput).subscribe((pharmacist: Array<PharmacistModel>) => {
      this.pharmacists = pharmacist;
    });


  }


  public scheduledCounseling(pharmacist: PharmacistModel, pharmacyChoosen: PharmacyModel, dateInput: Date, timeInput: string): void {
      this.counseling.pharmacistId = pharmacist.id;
      this.counseling.pharmacy = pharmacyChoosen;
      this.counseling.date = dateInput;
      this.counseling.time = timeInput.toString();
      this.counseling.price = pharmacyChoosen.counselingPrice;
      this.counseling.free = false;
      this.counseling.penalty = false;
      console.log('Stiglo front');
      this.patientService.scheduledCounseling(this.counseling);
  }
}

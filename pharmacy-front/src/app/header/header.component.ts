import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from '../_services/token-storage.service';
import {MedicineModel} from '../model/medicine.model';
import {ExaminationModel} from '../model/examination.model';
import {PatientModel} from '../model/patient.model';
import {PatientService} from '../services/patient.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLoggedIn = false;
  showADMIN = false;
  showUSER = false;
  showSupplier = false;
  username: string;
  role: string;
  private penalty: number;
  public patient: PatientModel;

  constructor(private tokenStorageService: TokenStorageService, private patientService: PatientService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      this.role = this.tokenStorageService.getUserType();
      this.username = this.tokenStorageService.getUsername();
      this.penalty = this.tokenStorageService.getPenalty();

    }
    if (this.role === 'ROLE_USER'){
      this.patientService.getPatient().subscribe((patient: PatientModel) => {
        this.patient = patient;
        console.log(this.patient.penalty);
        console.log(this.patient);
        this.penalty = this.patient.penalty;

      });
    }

  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }



}

import { Component, OnInit } from '@angular/core';
import {UserProfileService} from '../services/userProfile.service';
import {UserModel} from '../model/user.model';
import {TokenStorageService} from '../_services/token-storage.service';
import {PasswordChangerModel} from '../model/passwordChanger.model';
import {MedicineModel} from '../model/medicine.model';
import {PharmacyModel} from '../model/pharmacy.model';
import {PatientService} from '../services/patient.service';
import {LoyaltyProgram} from '../model/loyalty.model';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  public user: UserModel;
  showPWChange = false;
  form: any = {};
  private pw: PasswordChangerModel;
  error = false;
  isLoggedIn = false;
  role: string;
  showMedicines = false;
  showLoyalty = false;
  public medicines: Array<MedicineModel>;
  public loyalty: LoyaltyProgram;
  public pharmacy: PharmacyModel;

  constructor(
    private tokenStorageService: TokenStorageService,
    private userProfileService: UserProfileService,
    private patientService: PatientService
  ) { }

  ngOnInit(): void {
    this.userProfileService.getUserInfo(this.tokenStorageService.getUsername())
      .subscribe((userModel: UserModel) => {
        this.user = userModel;
      });
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      this.role = this.tokenStorageService.getUserType();
    }
  }

  updateProfile(form: any): void {
    if ( form.lastName){
      this.user.lastName = form.lastName;
    }
    if (form.firstName){
      this.user.firstName = form.firstName;
    }
    if (form.username){
      this.user.username = form.username;
    }
    if (form.email){
      this.user.email = form.email;
    }
    if (form.phoneNumber){
      this.user.phoneNumber = form.phoneNumber;
    }
    if (form.address){
      this.user.address = form.address;
    }
    if (form.city){
      this.user.city = form.city;
    }
    if (form.country){
      this.user.country = form.country;
    }
    this.userProfileService.updateProfile(this.user);

  }

  resetPassword(): void {
    this.error = false;
    if (this.form.confrmPassword !== this.form.password){
      this.error = true;
      return;
    }
    this.pw = new PasswordChangerModel(this.form.oldPassword, this.form.password);
    this.userProfileService.changePassword(this.pw);
    this.showPWChange = false;
  }

  showAllergyForPatient(): void {
    this.showMedicines = !this.showMedicines;
    if (!this.showMedicines){
      return;
    }
    this.patientService.getAllAllergyForPatient(this.user.username).subscribe((medicines: Array<MedicineModel>) => {
      this.medicines = medicines;
    });

  }

  showMyLoyalty(): void {
    this.showLoyalty = !this.showLoyalty;
    if (!this.showLoyalty){
      return;
    }
    this.patientService.getPatientLoyalty(this.user.username).subscribe((loyalty: LoyaltyProgram) => {
      this.loyalty = loyalty;
    });
  }
}

import { Component, OnInit } from '@angular/core';
import {AbsenceModel} from '../model/absence.model';
import {AbsenceService} from '../services/absence.service';
import {TokenStorageService} from '../_services/token-storage.service';

@Component({
  selector: 'app-absences',
  templateUrl: './absences.component.html',
  styleUrls: ['./absences.component.css']
})
export class AbsencesComponent implements OnInit {

  public absences: Array<AbsenceModel>;
  public absence: AbsenceModel;
  role: string;
  isShown = true;
  isDeclined = false;

  constructor(
    private abscenceService: AbsenceService,
    private tokenStorageService: TokenStorageService
  ) { }

  ngOnInit(): void {
    this.role = this.tokenStorageService.getUserType();
    if (this.role === 'ROLE_ADMIN'){
        this.getPharmaRequests();
    }
    if (this.role === 'ROLE_SYSADMIN'){
      this.getDermaRequests();
    }
  }


  private getDermaRequests(): void {
    this.abscenceService.getAllDermatologistRequests()
      .subscribe((absenceList: Array<AbsenceModel>) => {
        this.absences = absenceList;
      });
  }

  private getPharmaRequests(): void {
    this.abscenceService.getAllPharmacistRequests()
      .subscribe((absenceList: Array<AbsenceModel>) => {
        this.absences = absenceList;
      });
  }


  private solveAbsence(absence: AbsenceModel): void{
    this.absence = absence;
    this.isShown = ! this.isShown;
  }

  private decline(): void {
    this.absence.status = 'Odbijen';
    this.isDeclined = true;
  }

  private accept(): void{
    this.absence.status = 'Odobreno';
    this.abscenceService.acceptAbsencePha(this.absence);
    window.location.reload();
  }

  private confDecline(): void{
    this.absence.answerText = (document.getElementById('answer') as HTMLInputElement).value;
    this.abscenceService.declineAbsencePha(this.absence);
    window.location.reload();
  }
}

import { Component, OnInit } from '@angular/core';
import {AppointmentService} from '../services/appointment.service';
import {PatientService} from '../services/patient.service';
import {HttpClient} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {CounselingModel} from '../model/counseling.model';
import {ReservationModel} from '../model/reservation.model';

@Component({
  selector: 'app-reserved-medicines',
  templateUrl: './reserved-medicines.component.html',
  styleUrls: ['./reserved-medicines.component.css']
})
export class ReservedMedicinesComponent implements OnInit {

  constructor(private patientService: PatientService, private httpClient: HttpClient,
              private toast: ToastrService) { }
  public reservations: Array<ReservationModel>;

  public  currentDate = new Date();

  ngOnInit(): void {
    this.patientService.getAllReservedMedication().subscribe((reservationsList: Array<ReservationModel>) => {
      this.reservations = reservationsList;
    });

  }

  change(medicine): any {
    let date = medicine;
    date = Number(date);
    const d = new Date(date);
    const ds = d.toLocaleDateString();
    console.log(ds);
    return ds;
  }




  public cancelReservation(reservation: ReservationModel): void {
    this.patientService.cancelReservation(reservation);

  }
}

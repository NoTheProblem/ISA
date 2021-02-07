import { Component, OnInit, Input, ViewChild } from '@angular/core';

import {PharmacyModel} from '../model/pharmacy.model';
import {PharmacyService} from '../services/pharmacy.service';

@Component({
  selector: 'app-pharmacy',
  templateUrl: './pharmacy.component.html',
  styleUrls: ['./pharmacy.component.css']
})
export class PharmacyComponent implements OnInit {

  private pharmacy: PharmacyModel;
  isLoggedIn = false;
  role: string;

  constructor(
    private pharmacyService: PharmacyService
  ) {
  }


  ngOnInit(): void {
    this.pharmacyService.getPharmacyByAdmin().subscribe((pharmacy: PharmacyModel) => {
      console.log(pharmacy);
      this.pharmacy = pharmacy;
    });
  }



}

import { Component, OnInit, Input, ViewChild } from '@angular/core';

import {PharmacyModel} from '../model/pharmacy.model';
import {PharmacyService} from '../services/pharmacy.service';
import {ActivatedRoute } from '@angular/router';



@Component({
  selector: 'app-pharmacy',
  templateUrl: './pharmacy.component.html',
  styleUrls: ['./pharmacy.component.css']
})
export class PharmacyComponent implements OnInit {

  public pharmacy: PharmacyModel;
  isLoggedIn = false;
  role: string;

  constructor(
    private route: ActivatedRoute,
    private pharmacyService: PharmacyService
  ) {
  }


  ngOnInit(): void {
      this.pharmacyService.getPharmacyByAdmin().subscribe((pharmacy: PharmacyModel) => {
        this.pharmacy = pharmacy;
      });
  }
}

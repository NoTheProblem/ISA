import { Component, OnInit } from '@angular/core';
import {PharmacyModel} from '../model/pharmacy.model';
import {ActivatedRoute} from '@angular/router';
import {PharmacyService} from '../services/pharmacy.service';
import {TokenStorageService} from '../_services/token-storage.service';

@Component({
  selector: 'app-pharmacy-details',
  templateUrl: './pharmacy-details.component.html',
  styleUrls: ['./pharmacy-details.component.css']
})
export class PharmacyDetailsComponent implements OnInit {
  public pharmacy: PharmacyModel;
  isLoggedIn = false;
  public showMap = false;
  public adresa: string;

  constructor(
    private route: ActivatedRoute,
    private tokenStorageService: TokenStorageService,
    private pharmacyService: PharmacyService
  ) {
  }

  ngOnInit(): void {
    const routeParam = this.route.snapshot.paramMap;
    const pharmacyID = Number(routeParam.get('id'));
    this.pharmacyService.getPharmacyByID(pharmacyID).subscribe((pharmacy: PharmacyModel) => {
      this.pharmacy = pharmacy;
      this.adresa = this.pharmacy.city + ' ' + this.pharmacy.address + ' ' + this.pharmacy.city;
    });
    this.isLoggedIn = this.tokenStorageService.isLoggedIn();
  }

  subscribeToPromotions(): void{
    this.pharmacyService.subscribeToPromotions(this.pharmacy.id);
  }

  checkEPerscription(): void {
    return;
  }
}


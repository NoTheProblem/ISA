import { Component, OnInit } from '@angular/core';
import {PharmacyModel} from '../model/pharmacy.model';
import {PharmacyService} from '../services/pharmacy.service';
import {PromotionService} from '../services/promotion.service';

@Component({
  selector: 'app-promotions-patient',
  templateUrl: './promotions-patient.component.html',
  styleUrls: ['./promotions-patient.component.css']
})
export class PromotionsPatientComponent implements OnInit {

  public pharmacies: Array<PharmacyModel>;
  term: string;
  order: string;
  reverse = false;
  key = '';
  name: any;
  city: any;
  lowerGrade = 0;

  constructor(
    private pharmacyService: PharmacyService, private promotionService: PromotionService
  ) {
  }



  ngOnInit(): void {
    this.promotionService.getAllPharmaciesForPatient().subscribe((pharmacyList: Array<PharmacyModel>) => {
      this.pharmacies = pharmacyList;
    });
  }

  sort(key): void {
    this.key = key;
    this.reverse = !this.reverse;
  }

  search(): void {
    if (this.name === '') {
      this.ngOnInit();
    } else {
      this.pharmacies = this.pharmacies.filter(res => {
        return (
          res.name.toLocaleLowerCase().match(this.name.toLocaleLowerCase()) ||
          res.city.toLocaleLowerCase().match(this.name.toLocaleLowerCase()) ||
          res.pharmacyDescription.toLocaleLowerCase().match(this.name.toLocaleLowerCase()) ||
          res.country.toLocaleLowerCase().match(this.name.toLocaleLowerCase()) ||
          res.address.toLocaleLowerCase().match(this.name.toLocaleLowerCase())
        );

      });

    }
  }

  filterEvaluationGrade(): void {
    if (this.lowerGrade === 0) {
      this.ngOnInit();
    } else {
      this.pharmacies = this.pharmacies.filter(res => {
        return (
          res.evaluationGrade >= Number(this.lowerGrade)
        );

      });
    }
  }

}

import {Component, OnInit} from '@angular/core';
import {PharmacyService} from '../services/pharmacy.service';
import {PharmacyModel} from '../model/pharmacy.model';

@Component({
  selector: 'app-pharmacies',
  templateUrl: './pharmacies.component.html',
  styleUrls: ['./pharmacies.component.css']
})
export class PharmaciesComponent implements OnInit {

  public pharmacies: Array<PharmacyModel>;
  searchTerm: string;
  term: string;

  constructor(
    private pharmacyService: PharmacyService
  ) {
  }

  ngOnInit(): void {
    this.pharmacyService.getAll().subscribe((pharmacyList: Array<PharmacyModel>) => {
      console.log(pharmacyList);
      this.pharmacies = pharmacyList;
    });
  }

}

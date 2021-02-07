import { Component, OnInit } from '@angular/core';
import {MedicineModel} from '../model/medicine.model';
import {element} from 'protractor';

@Component({
  selector: 'app-promotions',
  templateUrl: './promotions.component.html',
  styleUrls: ['./promotions.component.css']
})
export class PromotionsComponent implements OnInit {
  isShown = true ;
  isSuccessful = false;
  form: any = {};
  constructor() { }

  ngOnInit(): void {
  }

  public toggle(): void {
    this.isShown = ! this.isShown;
  }

  onSubmit(): void {

  }

}

import { Component, OnInit, Input, ViewChild } from '@angular/core';

import {PharmacyModel} from '../model/pharmacy.model';
import {PharmacyService} from '../services/pharmacy.service';
import {ActivatedRoute } from '@angular/router';
import {EmployeeModel} from '../model/employee.model';
import {EmployeeService} from '../services/employee.service';



@Component({
  selector: 'app-pharmacy',
  templateUrl: './pharmacy.component.html',
  styleUrls: ['./pharmacy.component.css']
})
export class PharmacyComponent implements OnInit {
  private employees: Array<EmployeeModel>;
  public employeesBackUp: Array<EmployeeModel>;
  public pharmacy: PharmacyModel;
  buttonPress = false;
  naziv: string;
  role: string;
  searchTerm: string;
  term: string;
  reverse = false;
  lowerGrade = 0;
  name = '';
  key = '';
  isSuccessful = false;
  form: any = {};
  public showMap = false;
  public adresa: string;
  public formConf = false;
  public update = false;
  public showErr = false;

  constructor(
    private route: ActivatedRoute,
    private pharmacyService: PharmacyService,
    private employeeService: EmployeeService
  ) {
  }


  ngOnInit(): void {
      this.pharmacyService.getPharmacyByAdmin().subscribe((pharmacy: PharmacyModel) => {
        this.pharmacy = pharmacy;
        this.adresa = pharmacy.city + ' ' + pharmacy.address +  ' ' + pharmacy.city;
      });
      this.employees = new Array<EmployeeModel>();
  }

  phaList(): void {
    this.naziv = 'Farmaceuti';
    this.buttonPress = true;
    this.employeeService.getAllPharmacistsByPharmacyID(this.pharmacy.id).subscribe((pharmacistList: Array<EmployeeModel>) => {
      this.employees = pharmacistList;
    });
    this.employeesBackUp = this.employees;
  }

  dermaList(): void{
    this.naziv = 'Dermatolozi';
    this.buttonPress = true;
    this.employeeService.getAllDermaByPharmacyID(this.pharmacy.id).subscribe((dermaList: Array<EmployeeModel>) => {
      this.employees = dermaList;
    });
    this.employeesBackUp = this.employees;

  }

  public sort(key): void {
    this.key = key;
    this.reverse = !this.reverse;
  }

  public search(): void {
    if (this.name === '') {
      this.employees = this.employeesBackUp;
    } else {
      this.employees = this.employees.filter(res => {
        return (
          res.firstName.toLocaleLowerCase().match(this.name.toLocaleLowerCase()) ||
          res.lastName.toLocaleLowerCase().match(this.name.toLocaleLowerCase()) ||
          (res.firstName + ' ' + res.lastName).toLocaleLowerCase().match(this.name.toLocaleLowerCase()) ||
          (res.lastName + ' ' + res.firstName).toLocaleLowerCase().match(this.name.toLocaleLowerCase())
        );
      });

    }
  }

  azuriraj(form: any): void {
    this.showErr = false;
    this.formConf = false;
    if (form.name){
      this.pharmacy.name = form.name;
      this.formConf = true;
    }
    if (form.pharmacyDescription){
      this.pharmacy.pharmacyDescription = form.pharmacyDescription;
      this.formConf = true;
    }
    if (form.address){
      this.pharmacy.address = form.address;
      this.formConf = true;
    }
    if (this.formConf){
      this.pharmacyService.updatePharmacyInfo(this.pharmacy);
    }
    else {
      this.showErr = true;
    }
  }

  public filterEvaluationGrade(): void {
    if (this.lowerGrade === 0) {
      this.ngOnInit();
    } else {
      this.employees = this.employees.filter(res => {
        return (
          res.evaluationGrade >= Number(this.lowerGrade)
        );
      });
    }
  }
}

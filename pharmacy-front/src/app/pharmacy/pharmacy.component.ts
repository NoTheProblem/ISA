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
  name = '';
  key = '';
  public showMap = false;
  public adresa: string;

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
    this.buttonPress = !this.buttonPress;
    this.employeeService.getAllPharmacistsByPharmacyID(this.pharmacy.id).subscribe((pharmacistList: Array<EmployeeModel>) => {
      this.employees = pharmacistList;
    });
    this.employeesBackUp = this.employees;
  }

  dermaList(): void{
    this.naziv = 'Dermatolozi';
    this.buttonPress = !this.buttonPress;
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
          res.lastName.toLocaleLowerCase().match(this.name.toLocaleLowerCase())
        );
      });

    }
  }

}

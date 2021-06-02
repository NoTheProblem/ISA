import { Component, OnInit } from '@angular/core';
import {EmployeeService} from '../services/employee.service';
import {PharmacyService} from '../services/pharmacy.service';
import {PharmacyModel} from '../model/pharmacy.model';
import {TokenStorageService} from '../_services/token-storage.service';
import {Router} from '@angular/router';
import {EmployeeModel} from '../model/employee.model';

@Component({
  selector: 'app-pharmacy-report',
  templateUrl: './pharmacy-report.component.html',
  styleUrls: ['./pharmacy-report.component.css']
})
export class PharmacyReportComponent implements OnInit {
  public pharmacy: PharmacyModel;
  public employees: Array<EmployeeModel>;
  showGrades = false;
  empType = '';

  constructor(
    private employeeService: EmployeeService,
    private pharmacyService: PharmacyService,
    private tokenStorageService: TokenStorageService,
    private router: Router) { }

  ngOnInit(): void {
    if (this.tokenStorageService.permissionForPage('ROLE_ADMIN'))
    {
      this.router.navigate(['/error']);
      return;
    }
    this.pharmacyService.getPharmacyByAdmin().subscribe((pharmacy: PharmacyModel) => {
      this.pharmacy = pharmacy;
    });
  }

  showPharmaGradesFnc(): void {
    if (this.showGrades === true && this.empType === 'farmaceuta'){
      this.showGrades = false;
      return;
    }
    this.showGrades = true;
    this.empType = 'farmaceuta';
    this.employeeService.getAllPharmacistsByPharmacyID(this.pharmacy.id).subscribe((pharmacistList: Array<EmployeeModel>) => {
      this.employees = pharmacistList;
    });
  }

  showDermaGradesFnc(): void {
    if (this.showGrades === true && this.empType === 'dermatologa'){
      this.showGrades = false;
      return;
    }
    this.showGrades = true;
    this.empType = 'dermatologa';
    this.employeeService.getAllDermaByPharmacyID(this.pharmacy.id).subscribe((pharmacistList: Array<EmployeeModel>) => {
      this.employees = pharmacistList;
    });
  }
}

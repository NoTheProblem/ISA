import { Component, OnInit } from '@angular/core';
import {ExaminationModel} from '../model/examination.model';
import {ExaminationService} from '../services/examination.service';
import {PatientService} from '../services/patient.service';
import {DermatologistModel} from '../model/dermatologist.model';
import {PharmacistModel} from '../model/pharmacist.model';
import {PharmacyModel} from '../model/pharmacy.model';
import {AppointmentService} from '../services/appointment.service';
import {EvaluationModel} from '../model/evaluation.model';
import {MedicineService} from '../services/medicine.service';
import {MedicineModel} from '../model/medicine.model';
import {Router} from '@angular/router';
import {PharmacyService} from '../services/pharmacy.service';


@Component({
  selector: 'app-evaluation',
  templateUrl: './evaluation.component.html',
  styleUrls: ['./evaluation.component.css'],
})
export class EvaluationComponent implements OnInit {

  public examinations: Array<ExaminationModel>;
  public choosenDerma: DermatologistModel;
  public dermaId: number;
  public showInput: boolean;
  public dermas: Array<DermatologistModel>;
  public pharmas: Array<PharmacistModel>;
  public medicines: Array<MedicineModel>;
  public pharmacies: Array<PharmacyModel>;
  public idEvaluated: number;
  public evaluation: EvaluationModel = new EvaluationModel();


  public name: string;
  public key: any;
  public reverse: boolean;
  public examine: any;
  public grade: any;
  input: any;
  public type: string;

  constructor( private pharmacyService: PharmacyService,
               private medicineService: MedicineService, private examinationService: ExaminationService, private patientService: PatientService, private appointmentService: AppointmentService, private readonly router: Router
  ) {
  }

  ngOnInit(): void {
    this.examinationService.getAllHistoryDermaForEvaluation().subscribe((dermaList: Array<DermatologistModel>) => {
      this.dermas = dermaList;
    });
    this.appointmentService.getAllHistoryPhaForEvaluation().subscribe((phaList: Array<PharmacistModel>) => {
      this.pharmas = phaList;
    });
    this.medicineService.getAllHistoryReservationForEvaluation().subscribe((medList: Array<MedicineModel>) => {
      this.medicines = medList;
    });
    this.pharmacyService.getAllHistoryPharmacyForEvaluation().subscribe((phaList: Array<PharmacyModel>) => {
      this.pharmacies = phaList;
    });
  }

  sort(key): void {
    this.key = key;
    this.reverse = !this.reverse;
  }

  change(medicine): any {
    let date = medicine;
    date = Number(date);
    const d = new Date(date);
    const ds = d.toLocaleDateString();
    console.log(ds);
    return ds;
  }



  public evaluate(id: number, type: string): void {
      this.idEvaluated = id;
      this.showInput = true;
      this.type = type;
  }


  public addGrade(): void {
    this.evaluation.idOfEvaluated = this.idEvaluated;
    console.log(this.input);
    this.evaluation.grade = this.input;
    this.evaluation.typeOfEvaluation = this.type;
    this.patientService.addGrade(this.evaluation);
    this.router.navigate(['pharmacies']);
  }




  public modelChangeFn($event: any): void {
    this.input = $event.toString();

  }
}

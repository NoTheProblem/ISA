import { Component, OnInit } from '@angular/core';
import {ExaminationModel} from '../model/examination.model';
import {DermatologistModel} from '../model/dermatologist.model';
import {PharmacistModel} from '../model/pharmacist.model';
import {MedicineModel} from '../model/medicine.model';
import {PharmacyModel} from '../model/pharmacy.model';
import {EvaluationModel} from '../model/evaluation.model';
import {PharmacyService} from '../services/pharmacy.service';
import {MedicineService} from '../services/medicine.service';
import {ExaminationService} from '../services/examination.service';
import {PatientService} from '../services/patient.service';
import {AppointmentService} from '../services/appointment.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-change-evaluation',
  templateUrl: './change-evaluation.component.html',
  styleUrls: ['./change-evaluation.component.css']
})
export class ChangeEvaluationComponent implements OnInit {

  public examinations: Array<ExaminationModel>;
  public choosenDerma: DermatologistModel;
  public dermaId: number;
  public showInput: boolean;
  public dermas: Array<DermatologistModel>;
  public evaluations: Array<EvaluationModel>;
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
  public input: number;
  public type: string;

  constructor( private pharmacyService: PharmacyService,
               private medicineService: MedicineService, private examinationService: ExaminationService, private patientService: PatientService, private appointmentService: AppointmentService, private readonly router: Router
  ) {
  }

  ngOnInit(): void {
    this.patientService.getAllHistoryEvaluation().subscribe((evaluationList: Array<EvaluationModel>) => {
      this.evaluations = evaluationList;
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

    return ds;
  }



  public changeEvaluation(evaluationid, grade): void {
    this.evaluation.id = evaluationid;
    console.log(this.evaluation);
    this.showInput = true;
  }


  public changeGrade(): void {
    this.evaluation.grade = this.input;
    this.patientService.change(this.evaluation);
    this.router.navigate(['pharmacies']);
  }




  public modelChangeFn($event: any): void {
    this.input = $event.toString();

  }


}

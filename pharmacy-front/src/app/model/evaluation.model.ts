export class EvaluationModel {
  constructor(
    public id: number = 0,
    public idOfEvaluated: number = 0,
    public typeOfEvaluation: string = '',
    public numberOfEvaluation: number = 0,
    public grade: number = 0,
    public valid: boolean = true,
    public name: string = ''
  ) {
  }
}


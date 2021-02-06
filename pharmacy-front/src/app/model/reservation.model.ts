export class ReservationModel {
  constructor(
    public pickedUp: boolean = false,
    public endDate = new Date(),
    public price: number = 0,
    public pharmacyId: number = 0,
    public medicationId: number = 0

  ) {
  }
}

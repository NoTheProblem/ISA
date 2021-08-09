export class LoyaltyProgram {
  constructor(
    public id: number = 0,
    public downScore: number = 0,
    public upScore: number = 0,
    public discountPercentage: number = 0,
    public typeOfLoyalty: string = ''
  ) {
  }
}

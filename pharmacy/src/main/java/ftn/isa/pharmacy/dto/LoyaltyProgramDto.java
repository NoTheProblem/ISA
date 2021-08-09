package ftn.isa.pharmacy.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class LoyaltyProgramDto {



    private Long id;
    private int downScore;
    private int upScore;

    public LoyaltyProgramDto() {
    }

    private float discountPercentage;
    private String typeOfLoyalty;

    public LoyaltyProgramDto(int downScore, int upScore, float discountPercentage, String typeOfLoyalty) {

        this.downScore = downScore;
        this.upScore = upScore;
        this.discountPercentage = discountPercentage;
        this.typeOfLoyalty = typeOfLoyalty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDownScore() {
        return downScore;
    }

    public void setDownScore(int downScore) {
        this.downScore = downScore;
    }

    public int getUpScore() {
        return upScore;
    }

    public void setUpScore(int upScore) {
        this.upScore = upScore;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getTypeOfLoyalty() {
        return typeOfLoyalty;
    }

    public void setTypeOfLoyalty(String typeOfLoyalty) {
        this.typeOfLoyalty = typeOfLoyalty;
    }


}

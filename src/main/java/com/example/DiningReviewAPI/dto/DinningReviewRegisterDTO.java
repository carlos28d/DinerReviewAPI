package com.example.DiningReviewAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DinningReviewRegisterDTO {
    private String restaurantName;
    private String restaurantZipCode;
    private Integer peanutScore;
    private Integer eggScore;
    private Integer dairyScore;
    private String comment;
}
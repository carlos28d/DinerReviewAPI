package com.example.DiningReviewAPI.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    private String city;
    private String state;
    @Size(max=10) private String zipCode;
    private Boolean interestedPeanut;
    private Boolean interestedEgg;
    private Boolean interestedDairy;
}

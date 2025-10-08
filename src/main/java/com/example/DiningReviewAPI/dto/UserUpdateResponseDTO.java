package com.example.DiningReviewAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateResponseDTO {
    private Long id;
    private String name;
    private String city;
    private String state;
    private String zipCode;
    private Boolean interestedPeanut;
    private Boolean interestedEgg;
    private Boolean interestedDairy;
}

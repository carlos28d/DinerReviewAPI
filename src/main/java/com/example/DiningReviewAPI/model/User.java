package com.example.DiningReviewAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(unique = true, updatable = false, nullable = false)
    Long id;
    @NotNull
    @Column(unique = true, updatable = false, nullable = false)
    String name;
    @Setter
    String city;
    @Setter
    String state;
    @Setter
    String zipCode;
    @Setter
    Boolean interestedPeanut;
    @Setter
    Boolean interestedEgg;
    @Setter
    Boolean interestedDairy;
}

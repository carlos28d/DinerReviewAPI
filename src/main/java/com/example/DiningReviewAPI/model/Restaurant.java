package com.example.DiningReviewAPI.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Setter
    private String name;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiningReview> reviews;
    @Setter
    private String zipCode;

    public Restaurant(String name, String zipCode) {
        this.name = name;
        this.zipCode = zipCode;
    }
}

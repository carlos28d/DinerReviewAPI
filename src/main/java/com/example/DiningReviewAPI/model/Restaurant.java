package com.example.DiningReviewAPI.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    @Getter
    @Setter
    String restaurantName;
    @Getter
    @Setter
    List<DiningReview> peanutReview;
    @Getter
    @Setter
    List<DiningReview> eggReview;
    @Getter
    @Setter
    List<DiningReview> diaryReview;

}

package com.example.DiningReviewAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class DiningReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)// FK column
    private Restaurant restaurant;
    @Setter
    @Min(1)
    @Max(5)
    private Integer peanutScore;
    @Setter
    @Min(1)
    @Max(5)
    private Integer eggScore;
    @Setter
    @Min(1)
    @Max(5)
    private Integer dairyScore;
    @Setter
    private String comment;
    @Enumerated(EnumType.STRING)
    @Setter
    private ReviewStatus status = ReviewStatus.PENDING;





}

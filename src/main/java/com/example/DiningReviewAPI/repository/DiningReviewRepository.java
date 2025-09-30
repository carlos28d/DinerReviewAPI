package com.example.DiningReviewAPI.repository;

import com.example.DiningReviewAPI.model.Restaurant;
import com.example.DiningReviewAPI.model.ReviewStatus;
import org.springframework.data.repository.CrudRepository;
import com.example.DiningReviewAPI.model.DiningReview;

import java.util.List;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
    public List<DiningReview> findByStatus(ReviewStatus status);
    public List<DiningReview> findByStatusAndRestaurant(ReviewStatus status, Restaurant restaurant);
}

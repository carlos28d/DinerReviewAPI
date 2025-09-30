package com.example.DiningReviewAPI.repository;

import com.example.DiningReviewAPI.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    public Optional<Restaurant> findByName(String name);
    public Optional<Restaurant> findByNameAndZipCode(String name, String zipCode);
    public List<Restaurant> findByZipCodeAndReviewsIsNotNullOrderByZipCodeDesc(String zipCode);
}

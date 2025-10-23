package com.example.DiningReviewAPI.controller;

import com.example.DiningReviewAPI.dto.RestaurantRegisterDTO;
import com.example.DiningReviewAPI.model.Restaurant;
import com.example.DiningReviewAPI.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<RestaurantRegisterDTO> createRestaurant(@RequestBody RestaurantRegisterDTO newRestaurantRegisterDTO) {
        Restaurant restaurant = restaurantService.createRestaurant(newRestaurantRegisterDTO);
        return ResponseEntity.status(HttpStatus.OK).body(newRestaurantRegisterDTO);
    }
}
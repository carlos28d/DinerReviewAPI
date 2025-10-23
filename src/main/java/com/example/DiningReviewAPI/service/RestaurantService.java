package com.example.DiningReviewAPI.service;

import com.example.DiningReviewAPI.model.Restaurant;
import com.example.DiningReviewAPI.repository.RestaurantRepository;
import com.example.DiningReviewAPI.dto.RestaurantRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public Restaurant createRestaurant(RestaurantRegisterDTO restaurantRegisterDTO) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findByNameAndZipCode(restaurantRegisterDTO.getName(), restaurantRegisterDTO.getZipCode());
        if(optionalRestaurant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The restaurant with the same zip code and name already exists.");
        }
        Restaurant newRestaurant = new Restaurant(restaurantRegisterDTO.getName(), restaurantRegisterDTO.getZipCode());
        return this.restaurantRepository.save(newRestaurant);
    }
}

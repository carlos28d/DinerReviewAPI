package com.example.DiningReviewAPI.controller;

import com.example.DiningReviewAPI.model.User;
import com.example.DiningReviewAPI.repository.DiningReviewRepository;
import com.example.DiningReviewAPI.repository.RestaurantRepository;
import com.example.DiningReviewAPI.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@RestController
@RequestMapping("/dinning-reviews")
public class DiningReviewController {
    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public DiningReviewController(DiningReviewRepository diningReviewRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/users-creation")
    public User createUser(@RequestBody User newUser) {
        Optional<User> optionalUserEntity = this.userRepository.findByName(newUser.getName());
        if(optionalUserEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Choose another user name this is already taken");
        }
        if(newUser.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing name");
        }
        return this.userRepository.save(newUser);
    }
}

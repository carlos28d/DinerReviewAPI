package com.example.DiningReviewAPI.service;

import com.example.DiningReviewAPI.dto.UserUpdateDTO;
import com.example.DiningReviewAPI.model.DiningReview;
import com.example.DiningReviewAPI.model.Role;
import com.example.DiningReviewAPI.model.User;
import com.example.DiningReviewAPI.model.Restaurant;
import com.example.DiningReviewAPI.dto.UserRegistrationDTO;
import com.example.DiningReviewAPI.repository.RestaurantRepository;
import com.example.DiningReviewAPI.repository.DiningReviewRepository;
import com.example.DiningReviewAPI.exception.UserAlreadyExistsException;
import com.example.DiningReviewAPI.dto.DinningReviewRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiningReviewService {
    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserService userService;

    public DiningReview registerDinnerReview(String username, DinningReviewRegisterDTO diningReviewRegisteredDTO) {
        User userRegisteringReview = userService.displayUser(username);
        Optional<Restaurant> restaurantForReview = restaurantRepository.findByNameAndZipCode(diningReviewRegisteredDTO.getRestaurantName(), diningReviewRegisteredDTO.getRestaurantZipCode());
        if (restaurantForReview.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The restaurant you are trying to review does not exist currently");
        }
        Restaurant restaurant = restaurantForReview.get();
        DiningReview diningReview = new DiningReview(
                userRegisteringReview,
                restaurant,
                diningReviewRegisteredDTO.getPeanutScore(),
                diningReviewRegisteredDTO.getEggScore(),
                diningReviewRegisteredDTO.getDairyScore(),
                diningReviewRegisteredDTO.getComment()
        );
        return this.diningReviewRepository.save(diningReview);
    }
}

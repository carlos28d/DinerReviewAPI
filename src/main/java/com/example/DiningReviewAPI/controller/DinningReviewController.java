package com.example.DiningReviewAPI.controller;

import com.example.DiningReviewAPI.repository.DiningReviewRepository;
import com.example.DiningReviewAPI.repository.RestaurantRepository;
import com.example.DiningReviewAPI.service.DiningReviewService;
import com.example.DiningReviewAPI.service.UserService;
import com.example.DiningReviewAPI.security.CustomUserDetails;
import com.example.DiningReviewAPI.model.DiningReview;
import com.example.DiningReviewAPI.dto.DinningReviewRegisterDTO;
import com.example.DiningReviewAPI.dto.DinningReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dinning-reviews")
public class DinningReviewController {
    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserService userService;
    private final DiningReviewService diningReviewService;

    @PostMapping("/register")
    public ResponseEntity<DinningReviewResponseDTO> registerDiningReview(@RequestBody DinningReviewRegisterDTO diningReviewRegisterDTO, @AuthenticationPrincipal CustomUserDetails currentUser) {
        DiningReview newDiningReview = diningReviewService.registerDinnerReview(currentUser.getUsername(), diningReviewRegisterDTO);
        DinningReviewResponseDTO response = new DinningReviewResponseDTO(
                currentUser.getUsername(),
                diningReviewRegisterDTO.getRestaurantName(),
                diningReviewRegisterDTO.getRestaurantZipCode(),
                diningReviewRegisterDTO.getPeanutScore(),
                diningReviewRegisterDTO.getEggScore(),
                diningReviewRegisterDTO.getDairyScore(),
                diningReviewRegisterDTO.getComment()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

//    @GetMapping("/user/userProfile")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public String userProfile() {
//        return "Welcome to User Profile";
//    }


}

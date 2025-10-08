package com.example.DiningReviewAPI.controller;

import com.example.DiningReviewAPI.dto.UserRegistrationDTO;
import com.example.DiningReviewAPI.dto.UserResponseDTO;
import com.example.DiningReviewAPI.dto.UserUpdateDTO;
import com.example.DiningReviewAPI.dto.UserUpdateResponseDTO;
import com.example.DiningReviewAPI.security.CustomUserDetails;
import com.example.DiningReviewAPI.service.UserService;
import com.example.DiningReviewAPI.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserRegistrationDTO newUserDTO) {
        User savedUser = userService.registerUser(newUserDTO);
        UserResponseDTO response = new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getRole().name()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserUpdateResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdatesDTO, @AuthenticationPrincipal CustomUserDetails currentUser) {
        if (!currentUser.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot update another user");
        }
        User updatedUser = userService.updateUser(id, userUpdatesDTO);
        UserUpdateResponseDTO response = new UserUpdateResponseDTO(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getCity(),
                updatedUser.getState(),
                updatedUser.getZipCode(),
                updatedUser.getInterestedPeanut(),
                updatedUser.getInterestedEgg(),
                updatedUser.getInterestedDairy()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, this endpoint is not secure";
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }
}

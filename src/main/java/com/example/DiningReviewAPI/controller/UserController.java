package com.example.DiningReviewAPI.controller;

import com.example.DiningReviewAPI.dto.*;
import com.example.DiningReviewAPI.security.CustomUserDetails;
import com.example.DiningReviewAPI.service.UserService;
import com.example.DiningReviewAPI.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/{name}")
    public ResponseEntity<UserDisplayDTO> displayUser(@PathVariable String name) {
        User displayUser = userService.displayUser(name);
        UserDisplayDTO response = new UserDisplayDTO(
                displayUser.getName(),
                displayUser.getCity(),
                displayUser.getState(),
                displayUser.getZipCode(),
                displayUser.getInterestedPeanut(),
                displayUser.getInterestedEgg(),
                displayUser.getInterestedDairy()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PreAuthorize("#id == authentication.principal.id")
    @PutMapping("/{id}")
    public ResponseEntity<UserUpdateResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdatesDTO) {
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
}

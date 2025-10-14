package com.example.DiningReviewAPI.controller;

import com.example.DiningReviewAPI.dto.LoginDTO;
import com.example.DiningReviewAPI.dto.UserRegistrationDTO;
import com.example.DiningReviewAPI.dto.UserResponseDTO;
import com.example.DiningReviewAPI.model.User;
import com.example.DiningReviewAPI.service.JwtService;
import com.example.DiningReviewAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService; // if using JWT
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getName(), loginDTO.getPassword())
        );

        String token = jwtService.generateToken(auth);

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRegistrationDTO newUserDTO) {
        User savedUser = userService.registerUser(newUserDTO);

        UserResponseDTO response = new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getRole().name()
        );

        // Return 201 Created and the user info
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

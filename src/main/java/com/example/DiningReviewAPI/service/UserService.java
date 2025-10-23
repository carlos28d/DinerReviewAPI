package com.example.DiningReviewAPI.service;

import com.example.DiningReviewAPI.dto.UserUpdateDTO;
import com.example.DiningReviewAPI.model.Role;
import com.example.DiningReviewAPI.dto.UserRegistrationDTO;
import com.example.DiningReviewAPI.model.User;
import com.example.DiningReviewAPI.repository.UserRepository;
import com.example.DiningReviewAPI.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User displayUser(String name) {
        Optional<User> optionalUser = this.userRepository.findByName(name);
        if (optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this name is not found");
        }
        return optionalUser.get();
    }

    public User registerUser(UserRegistrationDTO newUserDTO) {
        if (newUserDTO.getName() == null || newUserDTO.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing name or password");
        }

        if (userRepository.findByName(newUserDTO.getName()).isPresent()) {
            throw new UserAlreadyExistsException("Username already taken");
        }

        String encodedPassword = passwordEncoder.encode(newUserDTO.getPassword());

        User user = new User(newUserDTO.getName(), encodedPassword, Role.ROLE_USER);

        return this.userRepository.save(user);
    }

    public User updateUser(Long id, UserUpdateDTO userUpdatesDTO) {
        User userToUpdate = this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user you are trying to update is not found"));
        Optional.ofNullable(userUpdatesDTO.getCity()).ifPresent(userToUpdate::setCity);
        Optional.ofNullable(userUpdatesDTO.getState()).ifPresent(userToUpdate::setState);
        Optional.ofNullable(userUpdatesDTO.getZipCode()).ifPresent(userToUpdate::setZipCode);
        Optional.ofNullable(userUpdatesDTO.getInterestedPeanut()).ifPresent(userToUpdate::setInterestedPeanut);
        Optional.ofNullable(userUpdatesDTO.getInterestedEgg()).ifPresent(userToUpdate::setInterestedEgg);
        Optional.ofNullable(userUpdatesDTO.getInterestedDairy()).ifPresent(userToUpdate::setInterestedDairy);
        return this.userRepository.save(userToUpdate);
    }
}

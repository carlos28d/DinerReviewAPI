package com.example.DiningReviewAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String name; // or "name" if that's what your user entity uses
    private String password;
}

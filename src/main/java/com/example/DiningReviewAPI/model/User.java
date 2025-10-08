package com.example.DiningReviewAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(unique = true, updatable = false, nullable = false)
    Long id;
    @NotNull
    @Column(unique = true, updatable = false, nullable = false)
    String name;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Setter
    String city;
    @Setter
    String state;
    @Setter
    String zipCode;
    @Setter
    Boolean interestedPeanut;
    @Setter
    Boolean interestedEgg;
    @Setter
    Boolean interestedDairy;

    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void assignRole(Role role) {
        this.role = role;
    }

    public User(String name, String encodedPassword, Role role) {
        this.name = name;
        this.password = encodedPassword;
        this.role = role;
    }

}

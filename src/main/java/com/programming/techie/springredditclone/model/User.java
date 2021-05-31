package com.programming.techie.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @org.springframework.data.annotation.Id
    private Integer ID;
    private String username;
    private String password;
    private String role;
//    @Id
//    private Long userId;
//
//    @NotBlank(message = "Password is required")
//    private String password;
//
//    @Email
//    @NotEmpty(message = "Email is required")
//    private String email;
//
//    private String firstName;
//
//    private String username;
//
//    private String lastName;
//
//    private String role;

}

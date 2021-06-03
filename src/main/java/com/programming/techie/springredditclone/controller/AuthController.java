package com.programming.techie.springredditclone.controller;

import com.programming.techie.springredditclone.dto.AuthenticationResponse;
import com.programming.techie.springredditclone.dto.LoginRequest;
import com.programming.techie.springredditclone.dto.RefreshTokenRequest;
import com.programming.techie.springredditclone.dto.RegisterRequest;
import com.programming.techie.springredditclone.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @PostMapping("/login")
    public <T> T login(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse login = authService.login(loginRequest);
        return login != null ? (T) login : (T) ResponseEntity.status(HttpStatus.OK).body("Not Allowed login twice, You already login !!!");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(name = "Authorization") String token) {
        authService.deleteToken(token);
        return ResponseEntity.status(HttpStatus.OK).body("Token Deleted Succcessfully");
    }
}

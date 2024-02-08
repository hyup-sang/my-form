package com.forms.api.auth.presentation;

import com.forms.api.auth.dto.request.SignInRequest;
import com.forms.api.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        authService.signIn(signInRequest);
        return ResponseEntity.noContent().build();
    }


}

package com.EdtAppProject.edtApp.controller;

import com.EdtAppProject.edtApp.dto.AuthResponse;
import com.EdtAppProject.edtApp.dto.LoginRequest;
import com.EdtAppProject.edtApp.dto.LogoutRequest;
import com.EdtAppProject.edtApp.dto.LogoutResponse;
import com.EdtAppProject.edtApp.dto.RegisterRequest;
import com.EdtAppProject.edtApp.service.AuthService;
import com.EdtAppProject.edtApp.service.UtilisateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UtilisateurService utilisateurService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok(authService.refreshToken(request.get("refreshToken")));
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(@RequestBody LogoutRequest request) {
        return ResponseEntity.ok(utilisateurService.logout(request));
    }
}

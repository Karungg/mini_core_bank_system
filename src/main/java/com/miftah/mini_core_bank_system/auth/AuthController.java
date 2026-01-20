package com.miftah.mini_core_bank_system.auth;

import com.miftah.mini_core_bank_system.dto.WebResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public WebResponse<String> register(
            @RequestBody @Valid RegisterRequest request) {
        authService.register(request);
        return WebResponse.success(HttpStatus.CREATED.value(), "User registered successfully", "OK");
    }

    @PostMapping("/login")
    public WebResponse<TokenResponse> login(
            @RequestBody @Valid LoginRequest request) {
        return WebResponse.success(HttpStatus.OK.value(), "Login successful", authService.login(request));
    }
}

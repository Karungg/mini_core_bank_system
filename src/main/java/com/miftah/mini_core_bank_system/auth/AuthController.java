package com.miftah.mini_core_bank_system.auth;

import com.miftah.mini_core_bank_system.dto.WebResponse;
import com.miftah.mini_core_bank_system.user.UserResponse;
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
    public WebResponse<UserResponse> register(
            @RequestBody @Valid RegisterRequest request) {
        UserResponse response = authService.register(request);
        return WebResponse.success(HttpStatus.CREATED.value(), "User registered successfully", response);
    }

    @PostMapping("/login")
    public WebResponse<TokenResponse> login(
            @RequestBody @Valid LoginRequest request) {
        return WebResponse.success(HttpStatus.OK.value(), "Login successful", authService.login(request));
    }
}

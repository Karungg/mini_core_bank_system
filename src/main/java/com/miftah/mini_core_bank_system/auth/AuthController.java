package com.miftah.mini_core_bank_system.auth;

import com.miftah.mini_core_bank_system.dto.WebResponse;
import com.miftah.mini_core_bank_system.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

    private final MessageSource messageSource;

    @PostMapping("/register")
    public WebResponse<UserResponse> register(
            @RequestBody @Valid RegisterRequest request) {
        UserResponse response = authService.register(request);
        return WebResponse.success(HttpStatus.CREATED.value(),
                messageSource.getMessage("success.register", null, LocaleContextHolder.getLocale()), response);
    }

    @PostMapping("/login")
    public WebResponse<TokenResponse> login(
            @RequestBody @Valid LoginRequest request) {
        return WebResponse.success(HttpStatus.OK.value(),
                messageSource.getMessage("success.login", null, LocaleContextHolder.getLocale()),
                authService.login(request));
    }
}

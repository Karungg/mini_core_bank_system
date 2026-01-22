package com.miftah.mini_core_bank_system.user;

import com.miftah.mini_core_bank_system.auth.RegisterRequest;
import com.miftah.mini_core_bank_system.dto.WebResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MessageSource messageSource;

    @PostMapping(path = "/admin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<UserResponse>> createAdmin(@RequestBody @Valid RegisterRequest request) {
        UserResponse response = userService.createAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(WebResponse.success(HttpStatus.CREATED.value(),
                        messageSource.getMessage("success.create", null, LocaleContextHolder.getLocale()),
                        response));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<UserResponse>> createUserWithProfile(
            @RequestBody @Valid CreateUserWithProfileRequest request) {
        UserResponse response = userService.createUserWithProfile(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(WebResponse.success(HttpStatus.CREATED.value(),
                        messageSource.getMessage("success.create", null, LocaleContextHolder.getLocale()),
                        response));
    }
}

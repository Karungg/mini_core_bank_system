package com.miftah.mini_core_bank_system.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "{validation.username.required}")
    private String username;
    @NotBlank(message = "{validation.password.required}")
    private String password;
}

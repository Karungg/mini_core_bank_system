package com.miftah.mini_core_bank_system.user;

import com.miftah.mini_core_bank_system.auth.RegisterRequest;
import java.util.UUID;

public interface UserService {

    UserResponse createAdmin(RegisterRequest request);

    UserResponse createUserWithProfile(CreateUserWithProfileRequest request);

    UserResponse updateAdmin(UUID id, UpdateUserRequest request);

    void deleteAdmin(UUID id);
}

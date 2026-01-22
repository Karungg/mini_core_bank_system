package com.miftah.mini_core_bank_system.user;

import com.miftah.mini_core_bank_system.auth.RegisterRequest;

public interface UserService {

    UserResponse createAdmin(RegisterRequest request);

    UserResponse createUserWithProfile(CreateUserWithProfileRequest request);
}

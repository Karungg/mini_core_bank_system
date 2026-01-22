package com.miftah.mini_core_bank_system.profile;

import com.miftah.mini_core_bank_system.user.User;

public interface ProfileService {
    ProfileResponse create(User user, ProfileRequest request);

    ProfileResponse get(User user);

    ProfileResponse update(User user, ProfileRequest request);
}

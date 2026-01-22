package com.miftah.mini_core_bank_system.profile;

import com.miftah.mini_core_bank_system.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProfileService {
    ProfileResponse create(User user, ProfileRequest request);

    ProfileResponse get(User user);

    ProfileResponse update(User user, ProfileRequest request);

    Page<ProfileResponse> getAll(Pageable pageable);

    ProfileResponse getById(UUID id);
}

package com.miftah.mini_core_bank_system.user;

import com.miftah.mini_core_bank_system.auth.RegisterRequest;
import com.miftah.mini_core_bank_system.exception.DuplicateResourceException;
import com.miftah.mini_core_bank_system.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileService profileService;

    @Override
    @Transactional
    public UserResponse createAdmin(RegisterRequest request) {
        log.info("Creating admin user: {}", request.getUsername());
        if (userRepository.existsByUsername(request.getUsername())) {
            log.warn("Username already exists: {}", request.getUsername());
            throw new DuplicateResourceException("username", "error.username.duplicate");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);

        log.info("Admin user created successfully: {}", user.getId());
        return toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse createUserWithProfile(CreateUserWithProfileRequest request) {
        log.info("Creating user with profile: {}", request.getUser().getUsername());
        if (userRepository.existsByUsername(request.getUser().getUsername())) {
            log.warn("Username already exists: {}", request.getUser().getUsername());
            throw new DuplicateResourceException("username", "error.username.duplicate");
        }

        User user = User.builder()
                .username(request.getUser().getUsername())
                .password(passwordEncoder.encode(request.getUser().getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        profileService.create(user, request.getProfile());

        log.info("User with profile created successfully: {}", user.getId());
        return toUserResponse(user);
    }

    private UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}

package com.miftah.mini_core_bank_system.user;

import com.miftah.mini_core_bank_system.auth.RegisterRequest;
import com.miftah.mini_core_bank_system.exception.DuplicateResourceException;
import com.miftah.mini_core_bank_system.profile.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private UserServiceImpl userService;

    private RegisterRequest registerRequest;
    private CreateUserWithProfileRequest createUserWithProfileRequest;
    private ProfileRequest profileRequest;

    @BeforeEach
    void setUp() {
        registerRequest = RegisterRequest.builder()
                .username("adminuser")
                .password("password")
                .build();

        profileRequest = ProfileRequest.builder()
                .type(ProfileType.KTP)
                .identityNumber("1234567890123456")
                .name("John Doe")
                .country("Indonesia")
                .placeOfBirth("Jakarta")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .gender(Gender.MALE)
                .phone("08123456789")
                .nationality("Indonesia")
                .build();

        createUserWithProfileRequest = CreateUserWithProfileRequest.builder()
                .user(RegisterRequest.builder()
                        .username("newuser")
                        .password("password")
                        .build())
                .profile(profileRequest)
                .build();
    }

    @Test
    void createAdmin_Success() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(UUID.randomUUID());
            return user;
        });

        UserResponse response = userService.createAdmin(registerRequest);

        assertNotNull(response);
        assertEquals(Role.ADMIN, response.getRole());
        assertEquals(registerRequest.getUsername(), response.getUsername());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createAdmin_DuplicateUsername_ThrowsException() {
        when(userRepository.existsByUsername(registerRequest.getUsername())).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> userService.createAdmin(registerRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void createUserWithProfile_Success() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(UUID.randomUUID());
            return user;
        });
        when(profileService.create(any(User.class), any(ProfileRequest.class)))
                .thenReturn(ProfileResponse.builder().build());

        UserResponse response = userService.createUserWithProfile(createUserWithProfileRequest);

        assertNotNull(response);
        assertEquals(Role.USER, response.getRole());
        assertEquals(createUserWithProfileRequest.getUser().getUsername(), response.getUsername());
        verify(userRepository).save(any(User.class));
        verify(profileService).create(any(User.class), any(ProfileRequest.class));
    }

    @Test
    void createUserWithProfile_DuplicateUsername_ThrowsException() {
        when(userRepository.existsByUsername(createUserWithProfileRequest.getUser().getUsername())).thenReturn(true);

        assertThrows(DuplicateResourceException.class,
                () -> userService.createUserWithProfile(createUserWithProfileRequest));
        verify(userRepository, never()).save(any(User.class));
        verify(profileService, never()).create(any(User.class), any(ProfileRequest.class));
    }
}

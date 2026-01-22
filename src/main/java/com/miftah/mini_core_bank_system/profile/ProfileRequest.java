package com.miftah.mini_core_bank_system.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequest {

    @NotNull(message = "{validation.profile.type.required}")
    private ProfileType type;

    @NotNull(message = "{validation.profile.expiryDate.required}")
    private LocalDate expiryDate;

    @NotBlank(message = "{validation.profile.identityNumber.required}")
    @Size(min = 16, max = 16, message = "{validation.profile.identityNumber.size}")
    private String identityNumber;

    @NotBlank(message = "{validation.profile.name.required}")
    @Size(max = 255, message = "{validation.profile.name.size}")
    private String name;

    @NotBlank(message = "{validation.profile.country.required}")
    @Size(max = 100, message = "{validation.profile.country.size}")
    private String country;

    @NotBlank(message = "{validation.profile.placeOfBirth.required}")
    @Size(max = 200, message = "{validation.profile.placeOfBirth.size}")
    private String placeOfBirth;

    @NotNull(message = "{validation.profile.dateOfBirth.required}")
    @Past(message = "{validation.profile.dateOfBirth.past}")
    private LocalDate dateOfBirth;

    @NotNull(message = "{validation.profile.gender.required}")
    private Gender gender;

    @NotBlank(message = "{validation.profile.phone.required}")
    @Size(max = 20, message = "{validation.profile.phone.size}")
    private String phone;

    @NotBlank(message = "{validation.profile.nationality.required}")
    @Size(max = 100, message = "{validation.profile.nationality.size}")
    private String nationality;

    private MaritalStatus maritalStatus;

    private String religion;

    private String address;

    private String building;

    private String rt;

    private String rw;

    private String province;

    private String occupation;

    private String officeName;

    private String officeAddress;

    private String officePhone;
}

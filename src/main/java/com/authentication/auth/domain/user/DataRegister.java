package com.authentication.auth.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataRegister(
    @NotBlank String name,
    @NotBlank String lastname,
    @NotBlank @Email String email,
    @NotBlank String password
) {

}

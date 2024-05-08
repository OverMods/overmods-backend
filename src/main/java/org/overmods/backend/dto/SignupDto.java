package org.overmods.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignupDto {
    @NotBlank
    public String username;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    public String password;

}

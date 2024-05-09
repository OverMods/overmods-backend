package org.overmods.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank
    public String username;

    @NotBlank
    public String password;
}

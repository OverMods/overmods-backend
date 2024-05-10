package org.overmods.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public class PatchUserDto {
    public Optional<@NotBlank @Email String> email;

    public Optional<@NotBlank  String> password;
}

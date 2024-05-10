package org.overmods.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatchUserDto {
    public Optional<@NotBlank @Email String> email;

    public Optional<@NotBlank  String> password;
    public Optional<@Min(value = 0) @Max(value = 5) Integer> siteRating;
}

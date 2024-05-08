package org.overmods.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.overmods.backend.dto.SignupDto;
import org.overmods.backend.error.ApiError;
import org.overmods.backend.model.User;
import org.overmods.backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@AllArgsConstructor
public class SignupController {
    private final UserService userService;

    @PostMapping
    public User signup(@Valid @RequestBody SignupDto dto) throws ApiError {
        return userService.signup(dto);
    }
}

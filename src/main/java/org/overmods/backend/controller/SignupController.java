package org.overmods.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.overmods.backend.dto.SignupDto;
import org.overmods.backend.dto.UserDto;
import org.overmods.backend.error.ApiError;
import org.overmods.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/signup")
@AllArgsConstructor
public class SignupController {
    private final UserService userService;

    @PostMapping
    public UserDto signup(@Valid @RequestBody SignupDto dto,
                          HttpServletRequest req, HttpServletResponse res) throws ApiError {
        return userService.signup(dto, req, res);
    }
}

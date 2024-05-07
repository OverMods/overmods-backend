package org.overmods.backend.controller;

import org.overmods.backend.dto.SignupDto;
import org.overmods.backend.error.ApiError;
import org.overmods.backend.model.User;
import org.overmods.backend.service.SignupService;
import org.overmods.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    private UserService userService;
    @Autowired
    private SignupService signupService;

    @PostMapping
    public User signup(@RequestBody SignupDto dto) throws ApiError {
        return signupService.signup(dto);
    }
}

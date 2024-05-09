package org.overmods.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.overmods.backend.dto.LoginDto;
import org.overmods.backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping
    public void login(@Valid @RequestBody LoginDto dto, HttpServletRequest req, HttpServletResponse res) {
        userService.login(dto, req, res);
    }
}

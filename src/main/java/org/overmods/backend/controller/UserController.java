package org.overmods.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.overmods.backend.dto.PatchUserDto;
import org.overmods.backend.error.ApiError;
import org.overmods.backend.model.User;
import org.overmods.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @PatchMapping()
    public void patchUser(@Valid @RequestBody PatchUserDto dto) throws ApiError {
        userService.patchUser(dto);
    }
}

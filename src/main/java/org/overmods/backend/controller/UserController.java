package org.overmods.backend.controller;

import lombok.AllArgsConstructor;
import org.overmods.backend.model.User;
import org.overmods.backend.repository.UserRepository;
import org.overmods.backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Integer id) {
        return userRepository.findUserById(id);
    }
}

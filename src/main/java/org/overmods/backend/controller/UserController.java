package org.overmods.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.overmods.backend.dto.ModCommentDto;
import org.overmods.backend.dto.PatchUserDto;
import org.overmods.backend.dto.UserDto;
import org.overmods.backend.error.ApiError;
import org.overmods.backend.model.User;
import org.overmods.backend.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/{id}")
    public Optional<UserDto> findById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @PatchMapping()
    public UserDto patchUser(@Valid @RequestBody PatchUserDto dto) throws ApiError {
        return userService.patchUser(dto);
    }

    @PutMapping("/avatar")
    public UserDto putAvatar(@RequestParam("avatar") MultipartFile avatar) throws ApiError {
        return userService.putAvatar(avatar);
    }

    @GetMapping("/comment")
    public List<ModCommentDto> findMyComments() {
        return userService.findMyComments();
    }
}

package org.overmods.backend.service;

import org.overmods.backend.dto.SignupDto;
import org.overmods.backend.model.User;
import org.overmods.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User signup(SignupDto dto) {
        User user = new User(dto);
        return userRepository.save(user);
    }
}

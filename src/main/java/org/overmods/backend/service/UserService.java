package org.overmods.backend.service;

import lombok.AllArgsConstructor;
import org.overmods.backend.dto.SignupDto;
import org.overmods.backend.error.ApiError;
import org.overmods.backend.error.UserAlreadyExists;
import org.overmods.backend.model.User;
import org.overmods.backend.model.UserRole;
import org.overmods.backend.repository.UserRepository;
import org.overmods.backend.security.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    public User signup(SignupDto dto) throws ApiError {
        boolean present = userRepository.findUserByUsername(dto.username).isPresent()
                || userRepository.findUserByEmail(dto.email).isPresent();
        if (present) {
            throw new UserAlreadyExists();
        }

        User user = new User();
        user.setUsername(dto.username);
        user.setEmail(dto.email);
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(dto.password));
        user.setRole(UserRole.USER);
        return userRepository.save(user);
    }
}

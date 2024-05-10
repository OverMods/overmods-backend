package org.overmods.backend.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.overmods.backend.dto.LoginDto;
import org.overmods.backend.dto.SignupDto;
import org.overmods.backend.error.ApiError;
import org.overmods.backend.error.UserAlreadyExists;
import org.overmods.backend.model.User;
import org.overmods.backend.model.UserRole;
import org.overmods.backend.repository.UserRepository;
import org.overmods.backend.security.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextHolderStrategy securityContextHolderStrategy
            = SecurityContextHolder.getContextHolderStrategy();
    private final SecurityContextRepository securityContextRepository
            = new HttpSessionSecurityContextRepository();

    private void createSession(String username, String password,
                               HttpServletRequest req, HttpServletResponse res) {
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken
                .unauthenticated(username, password);
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);

        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, req, res);
    }

    private void destroySession(HttpServletRequest request) throws ServletException {
        request.logout();
    }

    public User signup(SignupDto dto, HttpServletRequest req, HttpServletResponse res) throws ApiError {
        boolean present = userRepository.findUserByUsername(dto.username).isPresent()
                || userRepository.findUserByEmail(dto.email).isPresent();
        if (present) {
            throw new UserAlreadyExists();
        }

        User user = new User();
        user.setUsername(dto.username);
        user.setEmail(dto.email);
        user.setPassword(passwordEncoder.encode(dto.password));
        user.setRole(UserRole.USER);
        var out = userRepository.save(user);

        createSession(dto.username, dto.password, req, res);
        return out;
    }

    public void login(LoginDto dto, HttpServletRequest req, HttpServletResponse res) {
        createSession(dto.username, dto.password, req, res);
    }

    public void logout(HttpServletRequest req) throws ServletException {
        destroySession(req);
    }

    public Optional<User> findUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    public User getCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return findUserById(userDetails.getId()).get();
    }
}

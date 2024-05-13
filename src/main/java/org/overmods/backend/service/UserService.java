package org.overmods.backend.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.overmods.backend.dto.*;
import org.overmods.backend.error.ApiError;
import org.overmods.backend.error.InvalidParameter;
import org.overmods.backend.error.NotModified;
import org.overmods.backend.error.UserAlreadyExists;
import org.overmods.backend.model.User;
import org.overmods.backend.model.UserRole;
import org.overmods.backend.repository.ModCommentRepository;
import org.overmods.backend.repository.ModRepository;
import org.overmods.backend.repository.UserRepository;
import org.overmods.backend.security.UserDetailsImpl;
import org.overmods.backend.storage.StorageService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModRepository modRepository;
    private final ModCommentRepository modCommentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextHolderStrategy securityContextHolderStrategy
            = SecurityContextHolder.getContextHolderStrategy();
    private final SecurityContextRepository securityContextRepository
            = new HttpSessionSecurityContextRepository();
    private final StorageService storageService;

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

    // unlike getCurrentUser, which is secured by Spring Security,
    // this method can be called even if session has no user authenticated
    private Optional<User> getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
            return userRepository.findUserById(userDetails.getId());
        } else {
            return Optional.empty();
        }
    }

    private void destroySession(HttpServletRequest request) throws ServletException {
        request.logout();
    }

    public UserDto signup(SignupDto dto, HttpServletRequest req, HttpServletResponse res) throws ApiError {
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
        return new UserDto(user, true, true);
    }

    public Optional<UserDto> login(LoginDto dto, HttpServletRequest req, HttpServletResponse res) {
        createSession(dto.username, dto.password, req, res);

        return getLoggedUser().map(user -> new UserDto(user, true, true));
    }

    public void logout(HttpServletRequest req) throws ServletException {
        destroySession(req);
    }

    public Optional<UserDto> findUserById(Integer id) {
        return userRepository.findUserById(id)
                .map(user -> new UserDto(user, false, false));
    }

    public User getCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userRepository.findUserById(userDetails.getId()).orElseThrow(
                () -> new IllegalStateException("there's must be user session, but got no user"));
    }

    public UserDto patchUser(PatchUserDto dto) throws ApiError {
        User user = getCurrentUser();
        if (dto.email != null && dto.email.isPresent()) {
            String oldEmail = user.getEmail();
            String newEmail = dto.email.get();

            if (newEmail.length() < 1) {
                throw new InvalidParameter();
            } else if (newEmail.equals(oldEmail)) {
                throw new NotModified();
            }

            // check if there exists any user with requested email
            if (userRepository.findUserByEmail(newEmail).isPresent()) {
                throw new UserAlreadyExists();
            }

            // proceed to modify email
            userRepository.updateEmail(user.getId(), newEmail);
            user.setEmail(newEmail);
            user.setModified();
        } else if (dto.password != null && dto.password.isPresent()) {
            String oldPassword = user.getPassword();
            String newPassword = dto.password.get();

            if (newPassword.length() < 1) {
                throw new InvalidParameter();
            }
            String hashedPassword = passwordEncoder.encode(newPassword);
            if (hashedPassword.equals(oldPassword)) {
                throw new NotModified();
            }

            // proceed to modify password
            userRepository.updatePassword(user.getId(), hashedPassword);
            user.setPassword(hashedPassword);
            user.setModifiedPassword();
        } else if(dto.siteRating != null && dto.siteRating.isPresent()) {
            Integer newSiteRating = dto.siteRating.get();

            userRepository.updateSiteRating(user.getId(), newSiteRating);
            user.setSiteRating(newSiteRating);
            user.setModified();
        }

        // we're also modifying local object, since hibernation transaction may happen later,
        // than we fetch it from database, so, we're returning local modified copy
        return new UserDto(user, true, true);
    }

    public UserDto putAvatar(MultipartFile avatar) throws ApiError {
        User user = getCurrentUser();

        String newAvatar = storageService.store(avatar);

        userRepository.putAvatar(user.getId(), newAvatar);
        user.setAvatar(newAvatar);
        user.setModified();
        return new UserDto(user, true, true);
    }

    public List<ModCommentDto> findMyComments() {
        User user = getCurrentUser();
        return user.getComments()
                .stream()
                .map(ModCommentDto::new)
                .toList();
    }

    public List<ModDto> findMyMods() {
        User user = getCurrentUser();
        return user.getMods()
                .stream()
                .map(ModDto::new)
                .toList();
    }
}

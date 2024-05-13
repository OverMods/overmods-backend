package org.overmods.backend.dto;

import org.overmods.backend.model.User;

import java.sql.Timestamp;
import java.util.regex.Pattern;

public class UserDto {
    public final Integer id;
    public final String username;
    public final String email;
    public final String color;
    public final String avatar;
    public final String role;
    public final Integer siteRating;
    public final Timestamp registeredAt;
    public final Timestamp updatedAt;
    public final Timestamp passwordChanged;
    public final boolean banned;

    private static final Pattern OBSCURE_EMAIL_PATTERN = Pattern.compile("(^.|@[^@](?=[^@]*$)|\\.[^.]+$)|.");
    private static String obscureEmail(String email) {
        return OBSCURE_EMAIL_PATTERN.matcher(email).replaceAll("$1*");
    }

    public UserDto(User user, boolean hasEmail, boolean hasPasswordChanged) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = hasEmail ? obscureEmail(user.getEmail()) : null;
        this.color = user.getColor();
        this.avatar = user.getAvatar();
        this.role = user.getRole().toString();
        this.siteRating = user.getSiteRating();
        this.registeredAt = user.getRegisteredAt();
        this.updatedAt = user.getUpdatedAt();
        this.passwordChanged = hasPasswordChanged ? user.getPasswordChanged() : null;
        this.banned = user.getBanned();
    }
}

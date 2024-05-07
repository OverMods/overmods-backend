package org.overmods.backend.error;

public class UserNotFound extends ApiError {
    public UserNotFound() {
        super(4, "User not found");
    }
}

package org.overmods.backend.error;

public class UserAlreadyExists extends ApiError {
    public UserAlreadyExists() {
        super(9, "User already exists");
    }
}

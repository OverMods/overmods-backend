package org.overmods.backend.error;

public class InvalidPassword extends ApiError {
    public InvalidPassword() {
        super(5, "Invalid password");
    }
}

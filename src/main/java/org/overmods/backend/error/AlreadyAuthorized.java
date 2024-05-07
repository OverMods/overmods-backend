package org.overmods.backend.error;

public class AlreadyAuthorized extends ApiError {
    public AlreadyAuthorized() {
        super(6, "Already logged in");
    }
}

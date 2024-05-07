package org.overmods.backend.error;

public class InsufficientPrivileges extends ApiError {
    public InsufficientPrivileges() {
        super(3, "Insufficient privileges");
    }
}

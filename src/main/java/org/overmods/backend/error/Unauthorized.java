package org.overmods.backend.error;

public class Unauthorized extends ApiError {
    public Unauthorized() {
        super(2, "Unauthorized");
    }
}

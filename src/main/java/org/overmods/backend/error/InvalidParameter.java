package org.overmods.backend.error;

public class InvalidParameter extends ApiError {
    public InvalidParameter() {
        super(1, "Invalid parameter");
    }
}

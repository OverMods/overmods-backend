package org.overmods.backend.error;

public class NotFound extends ApiError {
    public NotFound() {
        super(7, "Not found");
    }
}

package org.overmods.backend.error;

public class NotModified extends ApiError {
    public NotModified() {
        super(10, "Not modified");
    }
}

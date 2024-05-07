package org.overmods.backend.error;

public class Banned extends ApiError {
    public Banned(String reason) {
        super(11, "Banned: " + reason);
    }
}

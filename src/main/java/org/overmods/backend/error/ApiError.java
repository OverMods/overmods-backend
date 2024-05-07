package org.overmods.backend.error;

public class ApiError extends Exception {
    public int code;
    public String message;

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

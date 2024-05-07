package org.overmods.backend.error;

public class ApiErrorResponse {
    public int code;
    public String message;

    public ApiErrorResponse(ApiError error) {
        this.code = error.code;
        this.message = error.message;
    }
}

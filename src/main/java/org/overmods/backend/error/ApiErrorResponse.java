package org.overmods.backend.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiErrorResponse {
    public int code;
    public String message;

    public ApiErrorResponse(ApiError error) {
        this.code = error.code;
        this.message = error.message;
    }

    public String serializeToString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.err.println(e);
            return "";
        }
    }
}

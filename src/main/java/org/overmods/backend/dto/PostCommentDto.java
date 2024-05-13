package org.overmods.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class PostCommentDto {
    @NotBlank
    public String comment;
}

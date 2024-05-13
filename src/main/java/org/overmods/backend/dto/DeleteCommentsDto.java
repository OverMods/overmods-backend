package org.overmods.backend.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class DeleteCommentsDto {
    @NotNull
    public List<Integer> ids;
}

package org.overmods.backend.dto;

import java.util.List;

public class ModCommentsDto {
    public final List<UserDto> user;
    public final List<ModCommentDto> comment;

    public ModCommentsDto(List<UserDto> users, List<ModCommentDto> comment) {
        this.user = users;
        this.comment = comment;
    }
}

package org.overmods.backend.dto;

import org.overmods.backend.model.ModComment;

import java.sql.Timestamp;

public class ModCommentDto {
    public final Integer id;
    public final Integer mod;
    public final Integer user;
    public final Timestamp commentedAt;
    public final String comment;
    public final Boolean deleted;

    public ModCommentDto(ModComment modComment) {
        this.id = modComment.getId();;
        this.mod = modComment.getMod().getId();
        this.user = modComment.getUser().getId();
        this.commentedAt = modComment.getCommentedAt();
        this.comment = modComment.getComment();
        this.deleted = modComment.getDeleted();
    }
}

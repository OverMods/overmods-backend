package org.overmods.backend.dto;

import org.overmods.backend.model.Mod;

import java.sql.Timestamp;

public class ModDto {
    public final Integer id;
    public final Integer game;
    public final String title;
    public final String logo;
    public final Integer author;
    public final String authorTitle;
    public final Float rating;
    public final Timestamp uploadedAt;
    public final String description;
    public final String gameVersion;
    public final String modVersion;
    public final String instruction;
    public final Integer downloaded;
    public final Integer fileSize;

    public ModDto(Mod mod) {
        this.id = mod.getId();
        this.game = mod.getGame().getId();
        this.title = mod.getTitle();
        this.logo = mod.getLogo();
        this.author = mod.getAuthor().getId();
        this.authorTitle = mod.getAuthorTitle();
        this.rating = mod.getRating();
        this.uploadedAt = mod.getUploadedAt();
        this.description = mod.getDescription();
        this.gameVersion = mod.getGameVersion();
        this.modVersion = mod.getModVersion();
        this.instruction = mod.getInstruction();
        this.downloaded = mod.getDownloaded();
        this.fileSize = mod.getFileSize();
    }
}

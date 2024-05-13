package org.overmods.backend.dto;

import org.overmods.backend.model.ModScreenshot;

public class ModScreenshotDto {
    public final Integer id;
    public final Integer mod;
    public final String screenshot;
    public final String title;
    public final String description;

    public ModScreenshotDto(ModScreenshot screenshot) {
        this.id = screenshot.getId();
        this.mod = screenshot.getMod().getId();
        this.screenshot = screenshot.getScreenshot();
        this.title = screenshot.getTitle();
        this.description = screenshot.getDescription();
    }
}

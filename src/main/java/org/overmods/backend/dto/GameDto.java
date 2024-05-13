package org.overmods.backend.dto;

import org.overmods.backend.model.Game;

public class GameDto {
    public final Integer id;
    public final String title;
    public final String shortTitle;
    public final String logo;

    public GameDto(Game game) {
        this.id = game.getId();
        this.title = game.getTitle();
        this.shortTitle = game.getShortTitle();
        this.logo = game.getLogo();
    }
}

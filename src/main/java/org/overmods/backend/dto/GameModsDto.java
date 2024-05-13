package org.overmods.backend.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GameModsDto {
    public final GameDto game;
    public final List<ModDto> mods;
}

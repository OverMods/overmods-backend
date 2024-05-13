package org.overmods.backend.dto;

import lombok.AllArgsConstructor;
import org.overmods.backend.model.Game;
import org.overmods.backend.model.Mod;

import java.util.List;

@AllArgsConstructor
public class GameModsDto {
    public final Game game;
    public final List<Mod> mods;
}

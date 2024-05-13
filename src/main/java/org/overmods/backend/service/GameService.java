package org.overmods.backend.service;

import lombok.AllArgsConstructor;
import org.overmods.backend.dto.GameModsDto;
import org.overmods.backend.error.ApiError;
import org.overmods.backend.error.NotFound;
import org.overmods.backend.model.Game;
import org.overmods.backend.repository.GameRepository;
import org.overmods.backend.repository.ModRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final ModRepository modRepository;

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public GameModsDto findModsByGame(String shortTitle) throws ApiError {
        Game game = gameRepository.findByShortTitle(shortTitle).orElseThrow(NotFound::new);
        return new GameModsDto(game, modRepository.findByGameId(game.getId()));
    }
}

package org.overmods.backend.service;

import lombok.AllArgsConstructor;
import org.overmods.backend.dto.GameDto;
import org.overmods.backend.dto.GameModsDto;
import org.overmods.backend.dto.ModDto;
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

    public List<GameDto> findAll() {
        return gameRepository.findAll()
                .stream()
                .map(GameDto::new)
                .toList();
    }

    public GameModsDto findModsByGame(String shortTitle) throws ApiError {
        Game game = gameRepository.findByShortTitle(shortTitle).orElseThrow(NotFound::new);
        return new GameModsDto(new GameDto(game),
                modRepository.findByGameId(game.getId())
                        .stream()
                        .map(ModDto::new)
                        .toList()
        );
    }
}

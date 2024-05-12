package org.overmods.backend.service;

import lombok.AllArgsConstructor;
import org.overmods.backend.model.Game;
import org.overmods.backend.model.Mod;
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

    public List<Mod> findModsByGameId(Integer id) {
        return modRepository.findByGameId(id);
    }
}

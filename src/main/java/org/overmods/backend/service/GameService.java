package org.overmods.backend.service;

import lombok.AllArgsConstructor;
import org.overmods.backend.model.Game;
import org.overmods.backend.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public List<Game> findAll() {
        return gameRepository.findAll();
    }
}

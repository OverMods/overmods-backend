package org.overmods.backend.controller;

import lombok.AllArgsConstructor;
import org.overmods.backend.model.Game;
import org.overmods.backend.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping
    public List<Game> findAll() {
        return gameService.findAll();
    }
}

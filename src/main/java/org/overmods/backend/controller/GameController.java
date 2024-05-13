package org.overmods.backend.controller;

import lombok.AllArgsConstructor;
import org.overmods.backend.dto.GameDto;
import org.overmods.backend.dto.GameModsDto;
import org.overmods.backend.error.ApiError;
import org.overmods.backend.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping
    public List<GameDto> findAll() {
        return gameService.findAll();
    }

    @GetMapping("/{shortTitle}")
    public GameModsDto findModsByGame(@PathVariable String shortTitle) throws ApiError {
        return gameService.findModsByGame(shortTitle);
    }
}

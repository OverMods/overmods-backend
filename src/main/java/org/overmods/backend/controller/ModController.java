package org.overmods.backend.controller;

import lombok.AllArgsConstructor;
import org.overmods.backend.dto.ModRatingDto;
import org.overmods.backend.dto.ModScreenshotDto;
import org.overmods.backend.model.Mod;
import org.overmods.backend.service.ModService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/mod")
@AllArgsConstructor
public class ModController {
    private final ModService modService;

    @GetMapping("/{id}")
    public Optional<Mod> findById(@PathVariable Integer id) { return modService.findById(id); }

    @GetMapping("/{id}/rating")
    public List<ModRatingDto> getModRatings(@PathVariable Integer id) {
        return modService.getModRatings(id);
    }

    @GetMapping("/{id}/screenshot")
    public List<ModScreenshotDto> getModScreenshots(@PathVariable Integer id) {
        return modService.getModScreenshots(id);
    }
}

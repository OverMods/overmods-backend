package org.overmods.backend.service;

import lombok.AllArgsConstructor;
import org.overmods.backend.dto.ModRatingDto;
import org.overmods.backend.dto.ModScreenshotDto;
import org.overmods.backend.model.Mod;
import org.overmods.backend.repository.ModRatingRepository;
import org.overmods.backend.repository.ModRepository;
import org.overmods.backend.repository.ModScreenshotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ModService {
    private final ModRepository modRepository;
    private final ModRatingRepository modRatingRepository;
    private final ModScreenshotRepository modScreenshotRepository;

    public Optional<Mod> findById(Integer id) { return modRepository.findById(id); }

    public List<ModRatingDto> getModRatings(Integer id) {
        return modRatingRepository.loadRatings(id)
                .stream()
                .map(ModRatingDto::new)
                .toList();
    }

    public List<ModScreenshotDto> getModScreenshots(Integer id) {
        return modScreenshotRepository.findAllByModId(id)
                .stream()
                .map(ModScreenshotDto::new)
                .toList();
    }
}

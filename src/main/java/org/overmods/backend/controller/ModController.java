package org.overmods.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.overmods.backend.dto.*;
import org.overmods.backend.error.ApiError;
import org.overmods.backend.model.Mod;
import org.overmods.backend.service.ModService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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

    @PutMapping("/{id}/logo")
    public ModDto putLogo(@PathVariable Integer id, @RequestParam("logo") MultipartFile logo) throws ApiError {
        return modService.putLogo(id, logo);
    }

    @PutMapping("/{id}/file")
    public ModDto putFile(@PathVariable Integer id, @RequestParam("file") MultipartFile file) throws ApiError {
        return modService.putFile(id, file);
    }

    @GetMapping("/{id}/comment")
    public List<ModCommentDto> getModComments(@PathVariable Integer id) {
        return modService.getModComments(id);
    }

    @PostMapping("/{id}/comment")
    public ModCommentDto postComment(@PathVariable Integer id,
                                     @Valid @RequestBody PostCommentDto dto) throws ApiError {
        return modService.postComment(id, dto);
    }

    @DeleteMapping("/comment")
    public HashMap<Integer, Object> deleteComments(@Valid @RequestBody DeleteCommentsDto dto) {
        return modService.deleteComments(dto);
    }

    @GetMapping("/{id}/rating")
    public List<ModRatingDto> getModRatings(@PathVariable Integer id) {
        return modService.getModRatings(id);
    }

    @GetMapping("/{id}/screenshot")
    public List<ModScreenshotDto> getModScreenshots(@PathVariable Integer id) {
        return modService.getModScreenshots(id);
    }
}

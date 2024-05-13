package org.overmods.backend.service;

import lombok.AllArgsConstructor;
import org.overmods.backend.dto.ModCommentDto;
import org.overmods.backend.dto.ModRatingDto;
import org.overmods.backend.dto.ModScreenshotDto;
import org.overmods.backend.dto.PostCommentDto;
import org.overmods.backend.error.ApiError;
import org.overmods.backend.error.NotFound;
import org.overmods.backend.model.Mod;
import org.overmods.backend.model.ModComment;
import org.overmods.backend.model.User;
import org.overmods.backend.repository.ModCommentRepository;
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
    private final ModCommentRepository modCommentRepository;
    private final ModRatingRepository modRatingRepository;
    private final ModScreenshotRepository modScreenshotRepository;
    private final UserService userService;

    public Optional<Mod> findById(Integer id) { return modRepository.findById(id); }

    public List<ModCommentDto> getModComments(Integer id) {
        return modCommentRepository.findAllByModId(id)
                .stream()
                .map(ModCommentDto::new)
                .toList();
    }

    public ModCommentDto postComment(Integer id, PostCommentDto dto) throws ApiError {
        User user = userService.getCurrentUser();
        Mod mod = modRepository.findById(id).orElseThrow(NotFound::new);

        ModComment modComment = new ModComment();
        modComment.setMod(mod);
        modComment.setUser(user);
        modComment.setComment(dto.comment);

        return new ModCommentDto(modCommentRepository.save(modComment));
    }

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

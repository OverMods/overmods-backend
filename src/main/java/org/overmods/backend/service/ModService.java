package org.overmods.backend.service;

import lombok.AllArgsConstructor;
import org.overmods.backend.dto.*;
import org.overmods.backend.error.ApiError;
import org.overmods.backend.error.ApiErrorResponse;
import org.overmods.backend.error.InsufficientPrivileges;
import org.overmods.backend.error.NotFound;
import org.overmods.backend.model.Mod;
import org.overmods.backend.model.ModComment;
import org.overmods.backend.model.User;
import org.overmods.backend.repository.ModCommentRepository;
import org.overmods.backend.repository.ModRatingRepository;
import org.overmods.backend.repository.ModRepository;
import org.overmods.backend.repository.ModScreenshotRepository;
import org.overmods.backend.storage.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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
    private final StorageService storageService;

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

        ModComment comment = new ModComment();
        comment.setMod(mod);
        comment.setUser(user);
        comment.setComment(dto.comment);

        return new ModCommentDto(modCommentRepository.save(comment));
    }

    // TODO: soft deletion
    public HashMap<Integer, Object> deleteComments(DeleteCommentsDto dto) {
        User user = userService.getCurrentUser();
        HashMap<Integer, Object> status = new HashMap<>();

        List<ModComment> comments = modCommentRepository.findAllById(dto.ids);
        for (ModComment comment : comments) {
            Integer commentId = comment.getId();
            if (comment.getUser().getId().equals(user.getId()) || user.isAdmin()) {
                // delete
                modCommentRepository.deleteById(commentId);
                status.put(commentId, Boolean.TRUE);
            } else {
                // access denied
                status.put(commentId, new ApiErrorResponse(new InsufficientPrivileges()));
            }
        }

        return status;
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

    public ModDto putLogo(Integer id, MultipartFile logo) throws ApiError {
        User user = userService.getCurrentUser();
        Mod mod = modRepository.findById(id).orElseThrow(NotFound::new);

        if (!user.hasModOwnership(mod) && !user.isAdmin()) {
            throw new InsufficientPrivileges();
        }

        String newLogo = storageService.store(logo);
        modRepository.putLogo(mod.getId(), newLogo);
        mod.setLogo(newLogo);

        return new ModDto(mod);
    }

    public ModDto putFile(Integer id, MultipartFile logo) throws ApiError {
        User user = userService.getCurrentUser();
        Mod mod = modRepository.findById(id).orElseThrow(NotFound::new);

        if (!user.hasModOwnership(mod) && !user.isAdmin()) {
            throw new InsufficientPrivileges();
        }

        String newFile = storageService.store(logo);
        modRepository.putFile(mod.getId(), newFile);
        mod.setLogo(newFile);

        return new ModDto(mod);
    }
}

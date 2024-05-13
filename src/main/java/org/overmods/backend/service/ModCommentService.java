package org.overmods.backend.service;

import lombok.AllArgsConstructor;
import org.overmods.backend.dto.ModCommentDto;
import org.overmods.backend.model.User;
import org.overmods.backend.repository.ModCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModCommentService {
    private final ModCommentRepository modCommentRepository;

    public List<ModCommentDto> findUserComments(User user) {
        return modCommentRepository.findAllByUser(user.getId())
                .stream()
                .map(ModCommentDto::new)
                .toList();
    }
}

package org.overmods.backend.repository;

import org.overmods.backend.model.ModComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModCommentRepository extends JpaRepository<ModComment, Integer> {
    List<ModComment> findAllByModId(Integer modId);
    List<ModComment> findAllByUserId(Integer userId);
}

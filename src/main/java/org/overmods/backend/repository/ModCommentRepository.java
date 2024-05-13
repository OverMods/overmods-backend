package org.overmods.backend.repository;

import org.overmods.backend.model.ModComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModCommentRepository extends JpaRepository<ModComment, Integer> {
    @Query("select c from ModComment c where c.mod.id = ?1")
    List<ModComment> findAllByMod(Integer mod);

    @Query("select c from ModComment c where c.user.id = ?1")
    List<ModComment> findAllByUser(Integer user);
}

package org.overmods.backend.repository;

import jakarta.transaction.Transactional;
import org.overmods.backend.model.Mod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModRepository extends JpaRepository<Mod, Integer> {
    List<Mod> findByGameId(Integer gameId);
    List<Mod> findByAuthorId(Integer authorId);

    @Modifying
    @Transactional
    @Query("update Mod m set m.logo = ?2 where m.id = ?1")
    void putLogo(Integer id, String newLogo);

    @Modifying
    @Transactional
    @Query("update Mod m set m.file = ?2 where m.id = ?1")
    void putFile(Integer id, String newFile);
}

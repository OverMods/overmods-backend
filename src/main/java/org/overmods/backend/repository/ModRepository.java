package org.overmods.backend.repository;

import org.overmods.backend.model.Mod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModRepository extends JpaRepository<Mod, Integer> {
    List<Mod> findByGameId(Integer gameId);
}

package org.overmods.backend.repository;

import org.overmods.backend.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Integer> {
    Optional<Game> findByShortTitle(String shortTitle);
}

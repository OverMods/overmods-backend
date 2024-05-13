package org.overmods.backend.repository;

import org.overmods.backend.model.ModRating;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModRatingRepository extends JpaRepository<ModRating, Integer> {
    @Query("select r from ModRating r where r.mod = ?1")
    List<ModRating> loadRatings(Integer modId);
}

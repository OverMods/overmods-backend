package org.overmods.backend.repository;

import org.overmods.backend.model.ModScreenshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModScreenshotRepository extends JpaRepository<ModScreenshot, Integer> {
    List<ModScreenshot> findAllByModId(Integer modId);
}

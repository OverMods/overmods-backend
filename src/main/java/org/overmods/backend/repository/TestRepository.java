package org.overmods.backend.repository;

import java.util.List;

import org.overmods.backend.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TestRepository extends JpaRepository<Test, Integer> {
    @Query("SELECT t FROM Test t")
    List<Test> findAll();
}

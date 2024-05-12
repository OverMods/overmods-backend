package org.overmods.backend.service;

import lombok.AllArgsConstructor;
import org.overmods.backend.model.Mod;
import org.overmods.backend.repository.ModRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ModService {
    private final ModRepository modRepository;

    public Optional<Mod> findById(Integer id) { return modRepository.findById(id); }
}

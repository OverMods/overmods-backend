package org.overmods.backend.controller;

import lombok.AllArgsConstructor;
import org.overmods.backend.model.Mod;
import org.overmods.backend.service.ModService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/mod")
@AllArgsConstructor
public class ModController {
    private final ModService modService;

    @GetMapping("/{id}")
    public Optional<Mod> findById(@PathVariable Integer id) { return modService.findById(id); }
}

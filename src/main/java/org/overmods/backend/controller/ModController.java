package org.overmods.backend.controller;

import lombok.AllArgsConstructor;
import org.overmods.backend.model.Mod;
import org.overmods.backend.service.ModService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/mod")
@AllArgsConstructor
public class ModController {
    private final ModService modService;

    @GetMapping("/{id}")
    public Optional<Mod> findById(@PathVariable Integer id) { return modService.findById(id); }
}

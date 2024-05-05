package org.overmods.backend.controller;

import java.util.List;

import org.overmods.backend.model.Test;
import org.overmods.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping
    public List<Test> tests() {
        return testService.findAllTests();
    }

    @PostMapping
    Test createTest(@RequestBody String value) {
        return testService.saveTest(value);
    }

    @DeleteMapping("/{id}")
    void deleteTest(@PathVariable Integer id) {
        testService.deleteTest(id);
    }
}

package org.overmods.backend.service;

import java.util.List;

import org.overmods.backend.model.Test;
import org.overmods.backend.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public List<Test> findAllTests() {
        return testRepository.findAll();
    }

    public Test saveTest(String value) {
        return testRepository.save(new Test(value));
    }

    public void deleteTest(Integer id) {
        testRepository.deleteById(id);
    }
}

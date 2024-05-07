package org.overmods.backend.repository;

import org.overmods.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findUserById(Integer id);
    public Optional<User> findUserByUsername(String username);
    public Optional<User> findUserByEmail(String email);
}

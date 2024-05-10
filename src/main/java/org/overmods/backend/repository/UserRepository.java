package org.overmods.backend.repository;

import jakarta.transaction.Transactional;
import org.overmods.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findUserById(Integer id);
    public Optional<User> findUserByUsername(String username);
    public Optional<User> findUserByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User u set u.email = ?2, u.updatedAt = CURRENT_TIMESTAMP() where u.id = ?1")
    public void updateEmail(Integer id, String newEmail);
}

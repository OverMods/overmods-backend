package org.overmods.backend.repository;

import jakarta.transaction.Transactional;
import org.overmods.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserById(Integer id);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User u set u.email = ?2, u.updatedAt = CURRENT_TIMESTAMP() where u.id = ?1")
    void updateEmail(Integer id, String newEmail);

    @Modifying
    @Transactional
    @Query("update User u set u.password = ?2, u.updatedAt = CURRENT_TIMESTAMP(), u.passwordChanged = CURRENT_TIMESTAMP() where u.id = ?1")
    void updatePassword(Integer id, String newPassword);

    @Modifying
    @Transactional
    @Query("update User u set u.siteRating = ?2, u.updatedAt = CURRENT_TIMESTAMP() where u.id = ?1")
    void updateSiteRating(Integer id, Integer newRating);

    @Modifying
    @Transactional
    @Query("update User u set u.avatar = ?2, u.updatedAt = CURRENT_TIMESTAMP() where u.id = ?1")
    void putAvatar(Integer id, String newAvatar);
}

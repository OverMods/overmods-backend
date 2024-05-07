package org.overmods.backend.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.overmods.backend.dto.SignupDto;

import java.sql.Timestamp;

@Entity
@Table(schema = "overmods", name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "username", nullable = false)
    public String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "color", length = 6)
    public String color;

    @Enumerated(EnumType.STRING)
    public UserRole role;

    @Column(name = "site_rating")
    public Integer siteRating;

    @CreationTimestamp
    @Column(name = "registered_at", nullable = false)
    public Timestamp registeredAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @CreationTimestamp
    @Column(name = "password_changed", nullable = false)
    private Timestamp passwordChanged;

    @Column(name = "banned", nullable = false)
    public boolean banned;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    public User(SignupDto dto) {
        this.username = dto.username;
        this.email = dto.email;
        this.password = dto.password;

        this.role = UserRole.ADMIN;
    }
}

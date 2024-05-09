package org.overmods.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(schema = "overmods", name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "username", nullable = false, unique = true)
    public String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
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
    @JsonIgnore
    private boolean deleted;
}

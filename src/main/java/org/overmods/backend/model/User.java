package org.overmods.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;

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

    @Column(name = "avatar")
    public String avatar;

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

    public void setModified() {
        setUpdatedAt(Timestamp.from(Instant.now()));
    }

    public void setModifiedPassword() {
        setModified();
        setPasswordChanged(Timestamp.from(Instant.now()));
    }
}

package org.overmods.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(schema = "overmods", name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "color", length = 6)
    private String color;

    @Column(name = "avatar")
    private String avatar;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "site_rating")
    private Integer siteRating;

    @CreationTimestamp
    @Column(name = "registered_at", nullable = false)
    private Timestamp registeredAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @CreationTimestamp
    @Column(name = "password_changed", nullable = false)
    private Timestamp passwordChanged;

    @Column(name = "banned", nullable = false)
    private Boolean banned;

    @Column(name = "deleted", nullable = false)
    @JsonIgnore
    private Boolean deleted;

    public void setModified() {
        setUpdatedAt(Timestamp.from(Instant.now()));
    }

    public void setModifiedPassword() {
        setModified();
        setPasswordChanged(Timestamp.from(Instant.now()));
    }

    @OneToMany(mappedBy = "author")
    @JsonManagedReference
    private Set<Mod> mods;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<ModComment> comments;

    public boolean isAdmin() {
        return getRole() == UserRole.ADMIN;
    }

    public boolean hasModOwnership(Mod mod) {
        return mod.getAuthor().getId().equals(getId())
                && getRole() == UserRole.MODDER;
    }
}

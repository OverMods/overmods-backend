package org.overmods.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(schema = "overmods", name = "mod")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Mod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "game", nullable = false)
    @JsonBackReference
    private Game game;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "logo", nullable = false)
    private String logo;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    @JsonBackReference
    private User author;

    @Column(name = "author_title", nullable = true)
    private String authorTitle;

    @Column(name = "rating", nullable = false)
    private Float rating = 0.0f;

    @CreationTimestamp
    @Column(name = "uploaded_at", nullable = false)
    private Timestamp uploadedAt;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "game_version", nullable = true)
    private String gameVersion;

    @Column(name = "mod_version", nullable = true)
    private String modVersion;

    @Column(name = "instruction", nullable = true, columnDefinition = "TEXT")
    private String instruction;

    @Column(name = "downloaded", nullable = false)
    private Integer downloaded = 0;

    @Column(name = "file", nullable = false)
    private String file;

    @Column(name = "file_size", nullable = false)
    private Integer fileSize;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @OneToMany(mappedBy = "mod")
    @JsonManagedReference
    private Set<ModComment> comments;
}

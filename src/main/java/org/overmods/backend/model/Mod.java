package org.overmods.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(schema = "overmods", name = "mod")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Mod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "game", nullable = false)
    @JsonBackReference
    public Game game;

    @Column(name = "title", nullable = false)
    public String title;

    @Column(name = "logo", nullable = false)
    public String logo;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    @JsonBackReference
    public User author;

    @Column(name = "author_title", nullable = true)
    public String authorTitle;

    @Column(name = "rating", nullable = false)
    public Float rating = 0.0f;

    @CreationTimestamp
    @Column(name = "uploaded_at", nullable = false)
    public Timestamp uploadedAt;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    public String description;

    @Column(name = "game_version", nullable = true)
    public String gameVersion;

    @Column(name = "mod_version", nullable = true)
    public String modVersion;

    @Column(name = "instruction", nullable = true, columnDefinition = "TEXT")
    public String instruction;

    @Column(name = "downloaded", nullable = false)
    public Integer downloaded = 0;

    @Column(name = "file", nullable = false)
    private String file;

    @Column(name = "file_size", nullable = false)
    public Integer fileSize;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;
}

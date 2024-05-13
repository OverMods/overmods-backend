package org.overmods.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(schema = "overmods", name = "mod_comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "mod", nullable = false)
    private Mod mod;

    @ManyToOne
    @JoinColumn(name = "\"user\"", nullable = false)
    private User user;

    @CreationTimestamp
    @Column(name = "commented_at", nullable = false)
    private Timestamp commentedAt;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "deleted")
    private Boolean deleted = false;
}

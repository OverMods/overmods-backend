package org.overmods.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(schema = "overmods", name = "game")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "short_title", nullable = false, unique = true)
    private String shortTitle;

    @Column(name = "logo", nullable = false)
    private String logo;

    @OneToMany(mappedBy = "game")
    @JsonManagedReference
    @JsonIgnore
    private Set<Mod> mods;
}

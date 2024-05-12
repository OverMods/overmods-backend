package org.overmods.backend.model;

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
    public Integer id;

    @Column(name = "title", nullable = false)
    public String title;

    @Column(name = "short_title", nullable = false)
    public String shortTitle;

    @Column(name = "logo", nullable = false)
    public String logo;

    @OneToMany(mappedBy = "game")
    @JsonManagedReference
    private Set<Mod> mods;
}

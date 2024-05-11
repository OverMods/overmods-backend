package org.overmods.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}

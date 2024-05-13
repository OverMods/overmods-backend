package org.overmods.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "overmods", name = "mod_screenshot")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModScreenshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "mod", nullable = false)
    private Mod mod;

    @Column(name = "screenshot", nullable = false)
    private String screenshot;

    @Column(name = "title", nullable = true)
    private String title;

    @Column(name = "description", nullable = true)
    private String description;
}

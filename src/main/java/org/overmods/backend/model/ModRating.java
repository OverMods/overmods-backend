package org.overmods.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "overmods", name = "mod_rating")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModRating {
    @Id
    private Integer mod;

    @Column(name = "\"user\"", nullable = false)
    private Integer user;

    @Column(name = "rating", columnDefinition = "SMALLINT")
    private Short rating;
}

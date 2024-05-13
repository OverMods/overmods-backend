package org.overmods.backend.dto;

import org.overmods.backend.model.ModRating;

public class ModRatingDto {
    public final Integer mod;
    public final Integer user;
    public final Short rating;

    public ModRatingDto(ModRating modRating) {
        this.mod = modRating.getMod();
        this.user = modRating.getUser();
        this.rating = modRating.getRating();
    }
}

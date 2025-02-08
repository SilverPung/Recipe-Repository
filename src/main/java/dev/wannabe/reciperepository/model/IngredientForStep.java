package dev.wannabe.reciperepository.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
public class IngredientForStep {

    @EmbeddedId
    private IngredientForStepId id;

    @Getter
    @Setter
    private String quantityNeeded;

    public IngredientForStep() {
    }

    public IngredientForStep(IngredientForStepId id, String quantityNeeded) {
        this.id = id;
        this.quantityNeeded = quantityNeeded;
    }

}

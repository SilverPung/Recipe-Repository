package dev.wannabe.reciperepository.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Embeddable
@Getter
@Setter
public class IngredientForStepId implements Serializable{

    @ManyToOne
    @JoinColumn(name="step_id", nullable=false)
    private RecipeProcess step;

    @ManyToOne
    @JoinColumn(name="ingredient_id", nullable=false)
    private Ingredient ingredient;

    public IngredientForStepId() {
    }

    public IngredientForStepId(RecipeProcess step, Ingredient ingredient) {
        this.step = step;
        this.ingredient = ingredient;
    }
}

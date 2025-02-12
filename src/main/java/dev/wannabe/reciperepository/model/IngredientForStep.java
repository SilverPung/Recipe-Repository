package dev.wannabe.reciperepository.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class IngredientForStep {

    @EmbeddedId
    private IngredientForStepId id;

    @ManyToOne
    @MapsId("ingredientId")
    private Ingredient ingredient;

    @ManyToOne
    @MapsId("stepId")
    private RecipeProcess recipeProcess;


    private String quantityNeeded;

    public IngredientForStep() {
    }

    public IngredientForStep(Ingredient ingredient, RecipeProcess recipeProcess, String quantityNeeded) {
        this.ingredient = ingredient;
        this.recipeProcess = recipeProcess;
        this.quantityNeeded = quantityNeeded;
        this.id = new IngredientForStepId(ingredient.getId(), recipeProcess.getId());
    }





}

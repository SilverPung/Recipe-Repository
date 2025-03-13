package dev.wannabe.reciperepository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"ingredient_id", "recipe_process_id"})})
public class IngredientForStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnoreProperties("ingredientForSteps")
    @ManyToOne
    private Ingredient ingredient;

    @JsonIgnoreProperties("ingredients")
    @ManyToOne
    private RecipeProcess recipeProcess;

    @NotNull
    private Double quantityNeeded;

    public IngredientForStep() {
    }

    public IngredientForStep(Ingredient ingredient, RecipeProcess recipeProcess, Double quantityNeeded) {
        this.ingredient = ingredient;
        this.recipeProcess = recipeProcess;
        this.quantityNeeded = quantityNeeded;
    }

    public void setData(IngredientForStep ingredientForStep) {
        this.ingredient = ingredientForStep.getIngredient();
        this.recipeProcess = ingredientForStep.getRecipeProcess();
        this.quantityNeeded = ingredientForStep.getQuantityNeeded();
    }







}

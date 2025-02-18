package dev.wannabe.reciperepository.model.request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IngredientForStepRequest {

    long stepId;

    long ingredientId;

    String quantityNeeded;

    public IngredientForStepRequest() {
    }

    public IngredientForStepRequest(long stepId, long ingredientId, String quantityNeeded) {
        this.stepId = stepId;
        this.ingredientId = ingredientId;
        this.quantityNeeded = quantityNeeded;
    }
}

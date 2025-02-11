package dev.wannabe.reciperepository.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
@Getter
@Setter
public class IngredientForStepId implements Serializable{


    private Long stepId;
    private Long ingredientId;

    public IngredientForStepId() {
    }

    public IngredientForStepId(Long stepId, Long ingredientId) {
        this.stepId = stepId;
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngredientForStepId)) return false;
        IngredientForStepId that = (IngredientForStepId) o;
        return stepId.equals(that.stepId) && ingredientId.equals(that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepId, ingredientId);
    }
}

package dev.wannabe.reciperepository.model.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeProcessRequest {

    long recipeId;
    int stepNumber;
    String name;
    String description;
    long duration;
    String workStation;
    String typeofWork;

    public RecipeProcessRequest() {
    }

    public RecipeProcessRequest(long recipeId, int stepNumber, String name, String description, long duration, String workStation, String typeofWork) {
        this.recipeId = recipeId;
        this.stepNumber = stepNumber;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.workStation = workStation;
        this.typeofWork = typeofWork;
    }
}

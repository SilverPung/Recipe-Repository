package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.model.RecipeProcess;
import dev.wannabe.reciperepository.model.Tool;
import dev.wannabe.reciperepository.service.RecipeProcessService;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeProcessController {

    private final RecipeProcessService recipeProcessService;

    public RecipeProcessController(RecipeProcessService recipeProcessService) {
        this.recipeProcessService = recipeProcessService;
    }


}

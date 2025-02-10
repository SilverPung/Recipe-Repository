package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.model.Recipe;
import dev.wannabe.reciperepository.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public Iterable<Recipe> getRecipes() {
        return recipeService.findAll();
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        return recipeService.findById(id);
    }

    @PostMapping("/recipes")
    public Recipe saveRecipe(@RequestBody Recipe recipe) {
        return recipeService.save(recipe);
    }

    @DeleteMapping("/recipes/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
    }

    @PutMapping("/recipes/{id}")
    public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
        recipe.setId(id);
        return recipeService.save(recipe);
    }


}

package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.model.Recipe;
import dev.wannabe.reciperepository.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> getRecipes() {
        return new ResponseEntity<>(recipeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        return Stream.of(recipeService.findById(id))
                .map(recipe -> new ResponseEntity<>(recipe, HttpStatus.OK))
                .findFirst()
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/recipes")
    public ResponseEntity<Recipe> saveRecipe(@Valid @RequestBody Recipe recipe) {
        return new ResponseEntity<>(recipeService.save(recipe), HttpStatus.CREATED);
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/recipes/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @Valid @RequestBody Recipe recipe) {
        recipe.setId(id);
        return new ResponseEntity<>(recipeService.update(recipe), HttpStatus.OK);
    }



}

package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.model.request.IngredientResponse;
import dev.wannabe.reciperepository.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.wannabe.reciperepository.model.Ingredient;

import java.util.List;

@RequestMapping("/api")
@RestController
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> getIngredients() {
        List<Ingredient> ingredients = ingredientService.findAll();
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }
    @GetMapping("/ingredients/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.findById(id);
        if (ingredient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

    @PostMapping("/ingredients")
    public ResponseEntity<Ingredient> createIngredient(@RequestBody IngredientResponse ingredientResponse) {
        Ingredient createdIngredient = ingredientService.save(ingredientResponse);
        return new ResponseEntity<>(createdIngredient, HttpStatus.CREATED);
    }

    @PutMapping("/ingredients/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody IngredientResponse ingredientResponse) {
        Ingredient updatedIngredient = ingredientService.update(id, ingredientResponse);
        return new ResponseEntity<>(updatedIngredient, HttpStatus.OK);
    }

    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<Long> deleteIngredient(@PathVariable Long id) {
        long deletedId = ingredientService.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.NO_CONTENT);
    }


}

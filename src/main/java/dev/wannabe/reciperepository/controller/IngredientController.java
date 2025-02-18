package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.model.request.IngredientResponse;
import dev.wannabe.reciperepository.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.wannabe.reciperepository.model.Ingredient;

import java.util.List;


@RequestMapping("/api/ingredients")
@RestController
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("")
    public ResponseEntity<List<Ingredient>> getIngredients() {
        return ResponseEntity.ok(ingredientService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<Ingredient> createIngredient(@RequestBody IngredientResponse ingredientResponse) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientService.save(ingredientResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody IngredientResponse ingredientResponse) {

        return ResponseEntity.ok(ingredientService.update(id, ingredientResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

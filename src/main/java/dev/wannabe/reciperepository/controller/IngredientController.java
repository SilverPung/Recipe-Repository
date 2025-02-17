package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.model.request.IngredientResponse;
import dev.wannabe.reciperepository.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.wannabe.reciperepository.model.Ingredient;

import java.util.List;
import java.util.stream.Stream;

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
        return new ResponseEntity<>(ingredientService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/ingredients/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        return Stream.of(ingredientService.findById(id))
                .map(ingredient -> new ResponseEntity<>(ingredient, HttpStatus.OK))
                .findFirst()
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/ingredients")
    public ResponseEntity<Ingredient> createIngredient(@RequestBody IngredientResponse ingredientResponse) {
        return new ResponseEntity<>(ingredientService.save(ingredientResponse), HttpStatus.CREATED);
    }

    @PutMapping("/ingredients/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody IngredientResponse ingredientResponse) {

        return new ResponseEntity<>(ingredientService.update(id, ingredientResponse), HttpStatus.OK);
    }

    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

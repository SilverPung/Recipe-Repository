package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.model.IngredientForStep;
import dev.wannabe.reciperepository.model.request.IngredientForStepRequest;
import dev.wannabe.reciperepository.service.IngredientForStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;


@RequestMapping("/api")
@RestController
public class IngredientForStepController {

    private final IngredientForStepService ingredientForStepService;

    @Autowired
    public IngredientForStepController(IngredientForStepService ingredientForStepService) {
        this.ingredientForStepService = ingredientForStepService;
    }

    @GetMapping("/ingredient-for-step")
    public ResponseEntity<List<IngredientForStep>> getIngredientForStep() {
        return new ResponseEntity<>(ingredientForStepService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/ingredient-for-step/{id}")
    public ResponseEntity<IngredientForStep> getIngredientForStepById(@PathVariable Long id) {
        return Stream.of(ingredientForStepService.findById(id))
                .map(ingredientForStep -> new ResponseEntity<>(ingredientForStep, HttpStatus.OK))
                .findFirst()
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/ingredient-for-step")
    public ResponseEntity<IngredientForStep> saveIngredientForStep(@RequestBody IngredientForStepRequest ingredientForStepRequest) {
        return new ResponseEntity<>(ingredientForStepService.save(ingredientForStepRequest), HttpStatus.CREATED);

    }

    @DeleteMapping("/ingredient-for-step/{id}")
    public ResponseEntity<Void> deleteIngredientForStep(@PathVariable Long id) {
        ingredientForStepService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/ingredient-for-step/{id}")
    public ResponseEntity<IngredientForStep> updateIngredientForStep(@PathVariable Long id, @RequestBody IngredientForStepRequest ingredientForStepRequest) {
        return new ResponseEntity<>(ingredientForStepService.update(id, ingredientForStepRequest), HttpStatus.OK);
    }
}

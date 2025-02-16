package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.model.IngredientForStep;
import dev.wannabe.reciperepository.model.request.IngredientForStepRequest;
import dev.wannabe.reciperepository.service.IngredientForStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        List<IngredientForStep> ingredientForStep = ingredientForStepService.findAll();
        return new ResponseEntity<>(ingredientForStep, HttpStatus.OK);
    }

    @GetMapping("/ingredient-for-step/{id}")
    public ResponseEntity<IngredientForStep> getIngredientForStepById(@PathVariable Long id) {
        IngredientForStep ingredientForStep = ingredientForStepService.findById(id);
        if (ingredientForStep == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ingredientForStep, HttpStatus.OK);
    }

    @PostMapping("/ingredient-for-step")
    public ResponseEntity<IngredientForStep> saveIngredientForStep(@RequestBody IngredientForStepRequest ingredientForStepRequest) {
        IngredientForStep savedIngredientForStep = ingredientForStepService.save(ingredientForStepRequest);
        return new ResponseEntity<>(savedIngredientForStep, HttpStatus.CREATED);

    }

    @DeleteMapping("/ingredient-for-step/{id}")
    public ResponseEntity<Long> deleteIngredientForStep(@PathVariable Long id) {
        long deletedId = ingredientForStepService.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.NO_CONTENT);
    }


    @PutMapping("/ingredient-for-step/{id}")
    public ResponseEntity<IngredientForStep> updateIngredientForStep(@PathVariable Long id, @RequestBody IngredientForStepRequest ingredientForStepRequest) {
        IngredientForStep updatedIngredientForStep = ingredientForStepService.update(id, ingredientForStepRequest);
        return new ResponseEntity<>(updatedIngredientForStep, HttpStatus.OK);
    }
}

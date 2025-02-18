package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.model.IngredientForStep;
import dev.wannabe.reciperepository.model.request.IngredientForStepRequest;
import dev.wannabe.reciperepository.service.IngredientForStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RequestMapping("/api/ingredient-for-step")
@RestController
public class IngredientForStepController {

    private final IngredientForStepService ingredientForStepService;

    @Autowired
    public IngredientForStepController(IngredientForStepService ingredientForStepService) {
        this.ingredientForStepService = ingredientForStepService;
    }

    @GetMapping("")
    public ResponseEntity<List<IngredientForStep>> getIngredientForStep() {
        return ResponseEntity.ok(ingredientForStepService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientForStep> getIngredientForStepById(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientForStepService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<IngredientForStep> saveIngredientForStep(@RequestBody IngredientForStepRequest ingredientForStepRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientForStepService.save(ingredientForStepRequest));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredientForStep(@PathVariable Long id) {
        ingredientForStepService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<IngredientForStep> updateIngredientForStep(@PathVariable Long id, @RequestBody IngredientForStepRequest ingredientForStepRequest) {
        return ResponseEntity.ok(ingredientForStepService.update(id, ingredientForStepRequest));
    }
}

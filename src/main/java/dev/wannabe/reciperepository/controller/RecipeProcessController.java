package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.model.RecipeProcess;
import dev.wannabe.reciperepository.model.request.RecipeProcessRequest;
import dev.wannabe.reciperepository.service.RecipeProcessService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/api")
public class RecipeProcessController {

    private final RecipeProcessService recipeProcessService;

    public RecipeProcessController(RecipeProcessService recipeProcessService) {
        this.recipeProcessService = recipeProcessService;
    }

    @GetMapping("/recipe-processes")
    public ResponseEntity<List<RecipeProcess>> getRecipeProcesses() {
        return ResponseEntity.ok(recipeProcessService.findAll());
    }

    @GetMapping("/recipe-processes/{id}")
    public ResponseEntity<RecipeProcess> getRecipeProcessById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeProcessService.findById(id));
    }

    @PostMapping("/recipe-processes")
    public ResponseEntity<RecipeProcess> saveRecipeProcess(@Valid @RequestBody RecipeProcessRequest recipeProcessRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeProcessService.save(recipeProcessRequest));
    }

    @DeleteMapping("/recipe-processes/{id}")
    public ResponseEntity<Void> deleteRecipeProcess(@PathVariable Long id) {
        recipeProcessService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/recipe-processes/{id}")
    public ResponseEntity<RecipeProcess> updateRecipeProcess(@PathVariable Long id, @Valid @RequestBody RecipeProcessRequest recipeProcessRequest) {
        return ResponseEntity.ok(recipeProcessService.update(id, recipeProcessRequest));
    }

    @PostMapping("/recipe-processes/{id}/tools/{toolId}")
    public ResponseEntity<RecipeProcess> addToolToRecipeProcess(@PathVariable Long id, @PathVariable Long toolId) {
        return ResponseEntity.ok(recipeProcessService.addTool(id, toolId));
    }

    @DeleteMapping("/recipe-processes/{id}/tools/{toolId}")
    public ResponseEntity<Void> removeToolFromRecipeProcess(@PathVariable Long id, @PathVariable Long toolId) {
        recipeProcessService.removeTool(id, toolId);
        return ResponseEntity.noContent().build();
    }

}

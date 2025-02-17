package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.model.RecipeProcess;
import dev.wannabe.reciperepository.model.request.RecipeProcessRequest;
import dev.wannabe.reciperepository.service.RecipeProcessService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class RecipeProcessController {

    private final RecipeProcessService recipeProcessService;

    public RecipeProcessController(RecipeProcessService recipeProcessService) {
        this.recipeProcessService = recipeProcessService;
    }

    @GetMapping("/recipe-processes")
    public ResponseEntity<List<RecipeProcess>> getRecipeProcesses() {
        return new ResponseEntity<>(recipeProcessService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/recipe-processes/{id}")
    public ResponseEntity<RecipeProcess> getRecipeProcessById(@PathVariable Long id) {
        return Stream.of(recipeProcessService.findById(id))
                .map(recipeProcess -> new ResponseEntity<>(recipeProcess, HttpStatus.OK))
                .findFirst()
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/recipe-processes")
    public ResponseEntity<RecipeProcess> saveRecipeProcess(@Valid @RequestBody RecipeProcessRequest recipeProcessRequest) {
        return new ResponseEntity<>(recipeProcessService.save(recipeProcessRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/recipe-processes/{id}")
    public ResponseEntity<Void> deleteRecipeProcess(@PathVariable Long id) {
        recipeProcessService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/recipe-processes/{id}")
    public ResponseEntity<RecipeProcess> updateRecipeProcess(@PathVariable Long id, @Valid @RequestBody RecipeProcessRequest recipeProcessRequest) {
        return new ResponseEntity<>(recipeProcessService.update(id, recipeProcessRequest), HttpStatus.OK);
    }

    @PostMapping("/recipe-processes/{id}/tools/{toolId}")
    public ResponseEntity<RecipeProcess> addToolToRecipeProcess(@PathVariable Long id, @PathVariable Long toolId) {
        return new ResponseEntity<>(recipeProcessService.addTool(id, toolId), HttpStatus.OK);
    }

    @DeleteMapping("/recipe-processes/{id}/tools/{toolId}")
    public ResponseEntity<Void> removeToolFromRecipeProcess(@PathVariable Long id, @PathVariable Long toolId) {
        recipeProcessService.removeTool(id, toolId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

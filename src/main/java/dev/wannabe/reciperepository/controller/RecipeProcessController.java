package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.model.RecipeProcess;
import dev.wannabe.reciperepository.model.request.RecipeProcessRequest;
import dev.wannabe.reciperepository.service.RecipeProcessService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class RecipeProcessController {

    private final RecipeProcessService recipeProcessService;

    public RecipeProcessController(RecipeProcessService recipeProcessService) {
        this.recipeProcessService = recipeProcessService;
    }

    @GetMapping("/recipe-processes")
    public ResponseEntity<Iterable<RecipeProcess>> getRecipeProcesses() {
        Iterable<RecipeProcess> recipeProcesses = recipeProcessService.findAll();
        return new ResponseEntity<>(recipeProcesses, HttpStatus.OK);
    }

    @GetMapping("/recipe-processes/{id}")
    public ResponseEntity<RecipeProcess> getRecipeProcessById(@PathVariable Long id) {
        RecipeProcess recipeProcess = recipeProcessService.findById(id);
        if (recipeProcess != null) {
            return new ResponseEntity<>(recipeProcess, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/recipe-processes")
    public ResponseEntity<RecipeProcess> saveRecipeProcess(@Valid @RequestBody RecipeProcessRequest recipeProcessRequest) {
        RecipeProcess savedRecipeProcess = recipeProcessService.save(recipeProcessRequest);
        return new ResponseEntity<>(savedRecipeProcess, HttpStatus.CREATED);
    }

    @DeleteMapping("/recipe-processes/{id}")
    public ResponseEntity<Long> deleteRecipeProcess(@PathVariable Long id) {
        long deletedId = recipeProcessService.deleteById(id);
        return new ResponseEntity<>(deletedId,HttpStatus.NO_CONTENT);
    }

    @PutMapping("/recipe-processes/{id}")
    public ResponseEntity<RecipeProcess> updateRecipeProcess(@PathVariable Long id, @Valid @RequestBody RecipeProcessRequest recipeProcessRequest) {
        RecipeProcess updatedRecipeProcess = recipeProcessService.update(id, recipeProcessRequest);
        return new ResponseEntity<>(updatedRecipeProcess, HttpStatus.OK);
    }

    @PostMapping("/recipe-processes/{id}/tools/{toolId}")
    public ResponseEntity<RecipeProcess> addToolToRecipeProcess(@PathVariable Long id, @PathVariable Long toolId) {
        RecipeProcess updatedRecipeProcess = recipeProcessService.addTool(id, toolId);
        return new ResponseEntity<>(updatedRecipeProcess, HttpStatus.OK);
    }

    @DeleteMapping("/recipe-processes/{id}/tools/{toolId}")
    public ResponseEntity<RecipeProcess> removeToolFromRecipeProcess(@PathVariable Long id, @PathVariable Long toolId) {
        RecipeProcess updatedRecipeProcess = recipeProcessService.removeTool(id, toolId);
        return new ResponseEntity<>(updatedRecipeProcess, HttpStatus.OK);
    }






}

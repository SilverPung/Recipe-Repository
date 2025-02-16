package dev.wannabe.reciperepository.service;

import dev.wannabe.reciperepository.model.RecipeProcess;
import dev.wannabe.reciperepository.model.Tool;
import dev.wannabe.reciperepository.model.request.RecipeProcessRequest;
import dev.wannabe.reciperepository.repository.RecipeProcessRepository;
import dev.wannabe.reciperepository.repository.RecipeRepository;
import dev.wannabe.reciperepository.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.HttpStatus;

@Service
public class RecipeProcessService {


    private final RecipeProcessRepository recipeProcessRepository;
    private final RecipeRepository recipeRepository;
    private final ToolRepository toolRepository;


    @Autowired
    public RecipeProcessService(RecipeProcessRepository recipeProcessRepository, RecipeRepository recipeRepository, ToolRepository toolRepository) {
        this.recipeProcessRepository = recipeProcessRepository;
        this.recipeRepository = recipeRepository;
        this.toolRepository = toolRepository;
    }

    public Iterable<RecipeProcess> findAll() {
        return recipeProcessRepository.findAll();
    }

    public RecipeProcess findById(Long id) {
        return recipeProcessRepository.findById(id).orElse(null);
    }

    public RecipeProcess save(RecipeProcessRequest recipeProcessRequest) {
        RecipeProcess recipeProcess = new RecipeProcess();

        return getRecipeProcess(recipeProcessRequest, recipeProcess);
    }

    public RecipeProcess update(long recipeProcess_id, RecipeProcessRequest recipeProcessRequest) {
        RecipeProcess recipeProcess = recipeProcessRepository.findById(recipeProcess_id).orElseThrow(
                () -> new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "RecipeProcess not found")
        );

        return getRecipeProcess(recipeProcessRequest, recipeProcess);
    }

    public long deleteById(Long id) {
        boolean exists = recipeProcessRepository.existsById(id);
        if (exists) {
            recipeProcessRepository.deleteById(id);
            return id;
        }
        return -1;
    }


    public RecipeProcess addTool(Long id, Long toolId) {
        RecipeProcess recipeProcess = recipeProcessRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RecipeProcess not found")
        );

        Tool tool = toolRepository.findById(toolId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tool not found")
        );

        recipeProcess.addTool(tool);

        return recipeProcessRepository.save(recipeProcess);
    }

    public RecipeProcess removeTool(Long id, Long toolId) {
        RecipeProcess recipeProcess = recipeProcessRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RecipeProcess not found")
        );

        Tool tool = toolRepository.findById(toolId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tool not found")
        );

        recipeProcess.removeTool(tool);

        return recipeProcessRepository.save(recipeProcess);
    }

    private RecipeProcess getRecipeProcess(RecipeProcessRequest recipeProcessRequest, RecipeProcess recipeProcess) {
        recipeProcess.setRecipe(recipeRepository.
                findById(recipeProcessRequest.getRecipeId()).
                orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found")
                ));

        recipeProcess.setData(recipeProcessRequest);

        try{
            return recipeProcessRepository.save(recipeProcess);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "RecipeProcess already exists");
        }
    }


}

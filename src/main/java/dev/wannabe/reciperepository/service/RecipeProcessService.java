package dev.wannabe.reciperepository.service;

import dev.wannabe.reciperepository.exception.UniqueForeignKeyException;
import jakarta.persistence.EntityNotFoundException;
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

import java.util.List;

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

    public List<RecipeProcess> findAll() {
        return recipeProcessRepository.findAll();
    }

    public RecipeProcess findById(Long id) {
        return recipeProcessRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("RecipeProcess on id " + id + " not found")
        );
    }

    public RecipeProcess save(RecipeProcessRequest recipeProcessRequest) {
        RecipeProcess recipeProcess = new RecipeProcess();

        return getRecipeProcess(recipeProcessRequest, recipeProcess);
    }

    public RecipeProcess update(long recipeProcess_id, RecipeProcessRequest recipeProcessRequest) {
        RecipeProcess recipeProcess = recipeProcessRepository.findById(recipeProcess_id).orElseThrow(
                () -> new EntityNotFoundException("RecipeProcess on id " + recipeProcess_id + " not found")
        );

        return getRecipeProcess(recipeProcessRequest, recipeProcess);
    }

    public void deleteById(Long id) {
        if(!recipeProcessRepository.existsById(id)){
            throw new EntityNotFoundException("RecipeProcess on id " + id + " not found");
        }
        recipeProcessRepository.deleteById(id);
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

    public void removeTool(Long id, Long toolId) {
        RecipeProcess recipeProcess = recipeProcessRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("RecipeProcess on id " + id + " not found")
        );

        Tool tool = toolRepository.findById(toolId).orElseThrow(
                () -> new EntityNotFoundException("Tool on id " + toolId + " not found")
        );

        recipeProcess.removeTool(tool);
        recipeProcessRepository.save(recipeProcess);

    }

    private RecipeProcess getRecipeProcess(RecipeProcessRequest recipeProcessRequest, RecipeProcess recipeProcess) {
        recipeProcess.setRecipe(recipeRepository.
                findById(recipeProcessRequest.getRecipeId()).
                orElseThrow(
                        () -> new EntityNotFoundException("Recipe on id " + recipeProcessRequest.getRecipeId() + " not found")
                ));

        recipeProcess.setData(recipeProcessRequest);

        try{
            return recipeProcessRepository.save(recipeProcess);
        } catch (DataIntegrityViolationException e) {
            if(e.getMessage().contains("Unique index or primary key violation")){
                throw new UniqueForeignKeyException("RecipeProcess with this recipe '"+recipeProcessRequest.getRecipeId()+"' and stepNumber '"+recipeProcessRequest.getStepNumber()+"' already exists");
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "RecipeProcess already exists", e);
            }
        }
    }


}

package dev.wannabe.reciperepository.service;

import dev.wannabe.reciperepository.model.RecipeProcess;
import dev.wannabe.reciperepository.model.Tool;
import dev.wannabe.reciperepository.model.request.RecipeProcessRequest;
import dev.wannabe.reciperepository.repository.RecipeProcessRepository;
import dev.wannabe.reciperepository.repository.RecipeRepository;
import dev.wannabe.reciperepository.repository.ToolRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RecipeProcessService {


    private final RecipeProcessRepository recipeProcessRepository;
    private final RecipeRepository recipeRepository;


    @Autowired
    public RecipeProcessService(RecipeProcessRepository recipeProcessRepository, RecipeRepository recipeRepository) {
        this.recipeProcessRepository = recipeProcessRepository;
        this.recipeRepository = recipeRepository;
    }

    public Iterable<RecipeProcess> findAll() {
        return recipeProcessRepository.findAll();
    }

    public RecipeProcess findById(Long id) {
        return recipeProcessRepository.findById(id).orElse(null);
    }

    public RecipeProcess save(RecipeProcess recipeProcess) {
        return recipeProcessRepository.save(recipeProcess);
    }

    public RecipeProcess save(RecipeProcessRequest recipeProcessRequest) {
        RecipeProcess recipeProcess = new RecipeProcess();

        recipeProcess.setRecipe(recipeRepository.findById(recipeProcessRequest.getRecipeId()).orElseThrow(
                () -> new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Recipe not found")
        ));

        recipeProcess.setData(recipeProcessRequest);


        return recipeProcessRepository.save(recipeProcess);
    }

    public RecipeProcess update(long recipeProcess_id, RecipeProcessRequest recipeProcessRequest) {
        RecipeProcess recipeProcess = recipeProcessRepository.findById(recipeProcess_id).orElseThrow(
                () -> new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "RecipeProcess not found")
        );

        recipeProcess.setRecipe(recipeRepository.findById(recipeProcessRequest.getRecipeId()).orElseThrow(
                () -> new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Recipe not found")
        ));

        recipeProcess.setData(recipeProcessRequest);

        return recipeProcessRepository.save(recipeProcess);
    }

    public long deleteById(Long id) {
        boolean exists = recipeProcessRepository.existsById(id);
        if (exists) {
            recipeProcessRepository.deleteById(id);
            return id;
        }
        return -1;
    }


}

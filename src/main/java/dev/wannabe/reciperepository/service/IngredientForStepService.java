package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.exception.UniqueForeignKeyException;
import jakarta.persistence.EntityNotFoundException;
import dev.wannabe.reciperepository.model.IngredientForStep;
import dev.wannabe.reciperepository.model.request.IngredientForStepRequest;
import dev.wannabe.reciperepository.repository.IngredientForStepRepository;
import dev.wannabe.reciperepository.repository.IngredientRepository;
import dev.wannabe.reciperepository.repository.RecipeProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

@Service
public class IngredientForStepService {

    private final IngredientForStepRepository ingredientForStepRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeProcessRepository recipeProcessRepository;

    @Autowired
    public IngredientForStepService(IngredientForStepRepository ingredientForStepRepository, IngredientRepository ingredientRepository, RecipeProcessRepository recipeProcessRepository) {
        this.ingredientForStepRepository = ingredientForStepRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeProcessRepository = recipeProcessRepository;
    }



    public List<IngredientForStep> findAll() {
        return ingredientForStepRepository.findAll();
    }

    public IngredientForStep findById(Long id) {
        return ingredientForStepRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("IngredientForStep on id " + id + " not found")
        );
    }

    public IngredientForStep save(IngredientForStepRequest ingredientForStepRequest) {
        IngredientForStep ingredientForStep = new IngredientForStep();
        return getIngredientForStep(ingredientForStepRequest, ingredientForStep);
    }



    public void deleteById(Long id) {
        if(!ingredientForStepRepository.existsById(id)){
            throw new EntityNotFoundException("IngredientForStep on id " + id + " not found");
        }
        ingredientForStepRepository.deleteById(id);
    }

    public IngredientForStep update(Long id, IngredientForStepRequest ingredientForStepRequest) {
        IngredientForStep ingredientForStep = ingredientForStepRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("IngredientForStep on id " + id + " not found")
        );

        return getIngredientForStep(ingredientForStepRequest, ingredientForStep);
    }

    private IngredientForStep getIngredientForStep(IngredientForStepRequest ingredientForStepRequest, IngredientForStep ingredientForStep) {
        ingredientForStep.setIngredient(ingredientRepository.
                findById(ingredientForStepRequest.getIngredientId()).
                orElseThrow(
                        () -> new EntityNotFoundException("Ingredient on id " + ingredientForStepRequest.getIngredientId() + " not found")
                ));

        ingredientForStep.setRecipeProcess(recipeProcessRepository.
                findById(ingredientForStepRequest.getStepId()).
                orElseThrow(
                        () -> new EntityNotFoundException("RecipeProcess on id " + ingredientForStepRequest.getStepId() + " not found")
                ));

        ingredientForStep.setQuantityNeeded(ingredientForStepRequest.getQuantityNeeded());

        try{
            return ingredientForStepRepository.save(ingredientForStep);
        } catch (DataIntegrityViolationException e) {
            if(e.getMessage().contains("Unique index or primary key violation")){
                throw new UniqueForeignKeyException("IngredientForStep with this Ingredient '" + ingredientForStepRequest.getIngredientId() + "' and Step '" + ingredientForStepRequest.getStepId() + "' already exists");
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "IngredientForStep already exists");
            }
        }
    }
}

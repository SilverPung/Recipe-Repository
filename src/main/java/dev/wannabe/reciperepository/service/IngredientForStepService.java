package dev.wannabe.reciperepository.service;


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
import org.springframework.dao.DuplicateKeyException;

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



    public Iterable<IngredientForStep> findAll() {
        return ingredientForStepRepository.findAll();



    }

    public IngredientForStep findById(Long id) {
        return ingredientForStepRepository.findById(id).orElse(null);
    }

    public IngredientForStep save(IngredientForStepRequest ingredientForStepRequest) {
        IngredientForStep ingredientForStep = new IngredientForStep();
        return getIngredientForStep(ingredientForStepRequest, ingredientForStep);
    }



    public long deleteById(Long id) {
        boolean exists = ingredientForStepRepository.existsById(id);
        if (exists) {
            ingredientForStepRepository.deleteById(id);
            return id;
        }
        return -1;
    }

    public IngredientForStep update(Long id, IngredientForStepRequest ingredientForStepRequest) {
        IngredientForStep ingredientForStep = ingredientForStepRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "IngredientForStep not found")
        );

        return getIngredientForStep(ingredientForStepRequest, ingredientForStep);
    }

    private IngredientForStep getIngredientForStep(IngredientForStepRequest ingredientForStepRequest, IngredientForStep ingredientForStep) {
        ingredientForStep.setIngredient(ingredientRepository.
                findById(ingredientForStepRequest.getIngredientId()).
                orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found")
                ));

        ingredientForStep.setRecipeProcess(recipeProcessRepository.
                findById(ingredientForStepRequest.getStepId()).
                orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RecipeProcess not found")
                ));

        ingredientForStep.setQuantityNeeded(ingredientForStepRequest.getQuantityNeeded());

        try{
            return ingredientForStepRepository.save(ingredientForStep);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "IngredientForStep already exists", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving IngredientForStep", e);
        }

    }
}

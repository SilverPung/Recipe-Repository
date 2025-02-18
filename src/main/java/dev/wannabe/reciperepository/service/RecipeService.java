package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.model.Recipe;
import dev.wannabe.reciperepository.repository.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recipe on id " + id + " not found")
        );
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
    public Recipe update(Recipe recipe) {
        if(!recipeRepository.existsById(recipe.getId())){
            throw new EntityNotFoundException("Recipe on id " + recipe.getId() + " not found");
        }
        return recipeRepository.save(recipe);
    }

    public void deleteById(Long id) {
        if(!recipeRepository.existsById(id)){
            throw new EntityNotFoundException("Recipe on id " + id + " not found");
        }
        recipeRepository.deleteById(id);
    }



}

package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.model.Recipe;
import dev.wannabe.reciperepository.repository.RecipeRepository;
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
        return recipeRepository.findById(id).orElse(null);
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public long deleteById(Long id) {
        boolean exists = recipeRepository.existsById(id);
        if (exists) {
            recipeRepository.deleteById(id);
            return id;
        }
        return -1;
    }



}

package dev.wannabe.reciperepository.service;

import dev.wannabe.reciperepository.model.RecipeProcess;
import dev.wannabe.reciperepository.model.Tool;
import dev.wannabe.reciperepository.repository.RecipeProcessRepository;
import dev.wannabe.reciperepository.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeProcessService {


    private final RecipeProcessRepository recipeProcessRepository;
    private final ToolRepository toolRepository;


    @Autowired
    public RecipeProcessService(RecipeProcessRepository recipeProcessRepository, ToolRepository toolRepository) {
        this.recipeProcessRepository = recipeProcessRepository;
        this.toolRepository = toolRepository;
    }







}

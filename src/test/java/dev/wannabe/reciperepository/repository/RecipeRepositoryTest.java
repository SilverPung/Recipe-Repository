package dev.wannabe.reciperepository.repository;

import dev.wannabe.reciperepository.model.Recipe;
import dev.wannabe.reciperepository.model.types.RecipeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;



    private Recipe savedRecipe;

    @BeforeEach
    void setUp() {
       savedRecipe = recipeRepository.save(new Recipe("Test Recipe", "Test Description", RecipeType.UNDEFINED));

    }

    @Test
    void findByIdShouldReturnRecipeWhenIdExists() {
        Optional<Recipe> foundRecipe = recipeRepository.findById(savedRecipe.getId());

        assertTrue(foundRecipe.isPresent());
        assertEquals("Test Recipe", foundRecipe.get().getName());
    }

    @Test
    void findAllShouldReturnEmptyListWhenNoRecipes() {
        recipeRepository.deleteAll();
        Iterable<Recipe> foundRecipes = recipeRepository.findAll();

        assertFalse(foundRecipes.iterator().hasNext());
    }

    @Test
    void saveShouldPersistRecipe() {


        assertNotNull(savedRecipe.getId());
        assertEquals("Test Recipe", savedRecipe.getName());
    }

    @Test
    void deleteByIdShouldRemoveRecipe() {
        recipeRepository.deleteById(1L);
        assertFalse(recipeRepository.findById(1L).isPresent());
    }

    @Test
    void updateShouldUpdateRecipe() {
        savedRecipe.setName("Updated Recipe");
        recipeRepository.save(savedRecipe);
        Optional<Recipe> foundRecipe = recipeRepository.findById(savedRecipe.getId());

        assertTrue(foundRecipe.isPresent());
        assertEquals("Updated Recipe", foundRecipe.get().getName());
        
    }

}

package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.model.Recipe;
import dev.wannabe.reciperepository.repository.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RecipeServiceTest {


    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;


    @Test
    public void findAllReturnsListOfRecipes() {
        List<Recipe> recipes = List.of(new Recipe(), new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipes);

        List<Recipe> result = recipeService.findAll();

        assertEquals(recipes, result);
    }

    @Test
    public void findAllReturnsEmptyListWhenNoRecipes() {
        when(recipeRepository.findAll()).thenReturn(List.of());

        List<Recipe> result = recipeService.findAll();

        assertEquals(0, result.size());
    }

    @Test
    public void findByIdReturnsRecipeWhenExists() {
        Recipe recipe = new Recipe();
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        Recipe result = recipeService.findById(1L);

        assertEquals(recipe, result);
    }

    @Test
    public void findByIdThrowsExceptionWhenNotExists() {

        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> recipeService.findById(1L));
    }

    @Test
    public void saveReturnsRecipe() {
        Recipe recipe = new Recipe();
        when(recipeRepository.save(recipe)).thenReturn(recipe);

        Recipe result = recipeService.save(recipe);

        assertEquals(recipe, result);
    }

    @Test
    public void updateReturnsRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeRepository.existsById(1L)).thenReturn(true);
        when(recipeRepository.save(recipe)).thenReturn(recipe);

        Recipe result = recipeService.update(recipe);

        assertEquals(recipe, result);

    }

    @Test
    public void updateThrowsExceptionWhenNotExists() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recipeService.update(recipe));
    }

    @Test
    public void deleteByIdDeletesRecipe() {
        when(recipeRepository.existsById(1L)).thenReturn(true);
        Mockito.doNothing().when(recipeRepository).deleteById(1L);


        assertDoesNotThrow(() -> recipeService.deleteById(1L));
    }

    @Test
    public void deleteThrowsExceptionWhenNotExists() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> recipeService.deleteById(1L));
    }
}

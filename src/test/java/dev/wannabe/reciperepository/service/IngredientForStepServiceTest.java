package dev.wannabe.reciperepository.service;

import dev.wannabe.reciperepository.exception.UniqueForeignKeyException;
import dev.wannabe.reciperepository.model.Ingredient;
import dev.wannabe.reciperepository.model.IngredientForStep;
import dev.wannabe.reciperepository.model.RecipeProcess;
import dev.wannabe.reciperepository.model.request.IngredientForStepRequest;
import dev.wannabe.reciperepository.repository.IngredientForStepRepository;
import dev.wannabe.reciperepository.repository.IngredientRepository;
import dev.wannabe.reciperepository.repository.RecipeProcessRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IngredientForStepServiceTest {

    @Mock
    private IngredientForStepRepository ingredientForStepRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private RecipeProcessRepository recipeProcessRepository;

    @InjectMocks
    private IngredientForStepService ingredientForStepService;

    @Test
    void findAllReturnsListOfIngredientsForStep() {
        List<IngredientForStep> ingredientsForStep = List.of(new IngredientForStep(), new IngredientForStep());
        when(ingredientForStepRepository.findAll()).thenReturn(ingredientsForStep);

        List<IngredientForStep> result = ingredientForStepService.findAll();

        assertEquals(ingredientsForStep, result);
    }

    @Test
    void findAllReturnsEmptyListWhenNoIngredientsForStep() {
        when(ingredientForStepRepository.findAll()).thenReturn(List.of());

        List<IngredientForStep> result = ingredientForStepService.findAll();

        assertEquals(0, result.size());
    }

    @Test
    void findByIdReturnsIngredientForStepWhenExists() {
        IngredientForStep ingredientForStep = new IngredientForStep();
        when(ingredientForStepRepository.findById(1L)).thenReturn(Optional.of(ingredientForStep));

        IngredientForStep result = ingredientForStepService.findById(1L);

        assertEquals(ingredientForStep, result);
    }

    @Test
    void findByIdThrowsExceptionWhenIngredientForStepDoesNotExist() {
        when(ingredientForStepRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ingredientForStepService.findById(1L));
    }

    @Test
    void savePersistsIngredientForStep() {
        IngredientForStepRequest ingredientForStepRequest = new IngredientForStepRequest(1L, 1L, 1D);
        when(ingredientRepository.findById(1L)).thenReturn(Optional.of(new Ingredient()));
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.of(new RecipeProcess()));
        IngredientForStep ingredientForStep = new IngredientForStep();
        when(ingredientForStepRepository.save(any(IngredientForStep.class))).thenReturn(ingredientForStep);

        IngredientForStep result = ingredientForStepService.save(ingredientForStepRequest);

        assertEquals(ingredientForStep, result);
    }

    @Test
    void saveThrowsExceptionWhenIngredientDoesNotExist() {
        IngredientForStepRequest ingredientForStepRequest = new IngredientForStepRequest(1L, 1L, 1D);
        when(ingredientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ingredientForStepService.save(ingredientForStepRequest));
    }

    @Test
    void saveThrowsExceptionWhenRecipeProcessDoesNotExist() {
        IngredientForStepRequest ingredientForStepRequest = new IngredientForStepRequest(1L, 1L, 1D);
        when(ingredientRepository.findById(1L)).thenReturn(Optional.of(new Ingredient()));
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ingredientForStepService.save(ingredientForStepRequest));
    }

    @Test
    void saveThrowsExceptionWhenUniqueConstraintViolated() {
        IngredientForStepRequest ingredientForStepRequest = new IngredientForStepRequest(1L, 1L, 1D);
        when(ingredientRepository.findById(1L)).thenReturn(Optional.of(new Ingredient()));
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.of(new RecipeProcess()));
        when(ingredientForStepRepository.save(any(IngredientForStep.class))).thenThrow(new DataIntegrityViolationException("Unique index or primary key violation"));

        assertThrows(UniqueForeignKeyException.class, () -> ingredientForStepService.save(ingredientForStepRequest));
    }

    @Test
    void updateReturnsUpdatedIngredientForStep() {
        IngredientForStep ingredientForStep = new IngredientForStep();
        ingredientForStep.setId(1L);
        IngredientForStepRequest ingredientForStepRequest = new IngredientForStepRequest(1L, 1L, 1D);
        when(ingredientForStepRepository.findById(1L)).thenReturn(Optional.of(ingredientForStep));
        when(ingredientRepository.findById(1L)).thenReturn(Optional.of(new Ingredient()));
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.of(new RecipeProcess()));
        when(ingredientForStepRepository.save(any(IngredientForStep.class))).thenReturn(ingredientForStep);

        IngredientForStep result = ingredientForStepService.update(1L, ingredientForStepRequest);

        assertEquals(ingredientForStep, result);
    }

    @Test
    void updateThrowsExceptionWhenIngredientForStepDoesNotExist() {
        IngredientForStepRequest ingredientForStepRequest = new IngredientForStepRequest(1L, 1L, 1D);
        when(ingredientForStepRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ingredientForStepService.update(1L, ingredientForStepRequest));
    }

    @Test
    void deleteRemovesIngredientForStep() {
        when(ingredientForStepRepository.existsById(1L)).thenReturn(true);

        ingredientForStepService.deleteById(1L);
    }

    @Test
    void deleteThrowsExceptionWhenIngredientForStepDoesNotExist() {
        when(ingredientForStepRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> ingredientForStepService.deleteById(1L));
    }
}
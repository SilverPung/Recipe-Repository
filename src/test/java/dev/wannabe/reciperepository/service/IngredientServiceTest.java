package dev.wannabe.reciperepository.service;

import dev.wannabe.reciperepository.model.Ingredient;
import dev.wannabe.reciperepository.model.Supplier;
import dev.wannabe.reciperepository.model.request.IngredientResponse;
import dev.wannabe.reciperepository.repository.IngredientRepository;
import dev.wannabe.reciperepository.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private IngredientService ingredientService;

    @Test
    void findAllReturnsListOfIngredients() {
        List<Ingredient> ingredients = List.of(new Ingredient(), new Ingredient());
        when(ingredientRepository.findAll()).thenReturn(ingredients);

        List<Ingredient> result = ingredientService.findAll();

        assertEquals(ingredients, result);
    }

    @Test
    void findAllReturnsEmptyListWhenNoIngredients() {
        when(ingredientRepository.findAll()).thenReturn(List.of());

        List<Ingredient> result = ingredientService.findAll();

        assertEquals(0, result.size());

    }

    @Test
    void findByIdReturnsIngredientWhenExists() {
        Ingredient ingredient = new Ingredient();
        when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));

        Ingredient result = ingredientService.findById(1L);

        assertEquals(ingredient, result);
    }

    @Test
    void findByIdThrowsExceptionWhenIngredientDoesNotExist() {
        when(ingredientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ingredientService.findById(1L));


    }

    @Test
    void savePersistsIngredient() {
        IngredientResponse ingredientResponse = new IngredientResponse(1L, "name",new Date(), 1D, "INGREDIENT","kg");
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(new Supplier()));
        Ingredient ingredient = new Ingredient();
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(ingredient);

        Ingredient result = ingredientService.save(ingredientResponse);

        assertEquals(ingredient, result);
    }

    @Test
    void saveThrowsExceptionWhenSupplierDoesNotExist() {
        IngredientResponse ingredientResponse = new IngredientResponse(1L, "name",new Date(), 1D, "INGREDIENT","kg");
        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ingredientService.save(ingredientResponse));
    }

    @Test
    void updateReturnsUpdatedIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        IngredientResponse ingredientResponse = new IngredientResponse(1L, "name",new Date(), 1D, "INGREDIENT","kg");
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(new Supplier()));
        when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(ingredient);

        Ingredient result = ingredientService.update(1L, ingredientResponse);

        assertEquals(ingredient, result);
    }

    @Test
    void updateThrowsExceptionWhenIngredientDoesNotExist() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        IngredientResponse ingredientResponse = new IngredientResponse(1L, "name",new Date(), 1D, "INGREDIENT","kg");
        when(ingredientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ingredientService.update(1L, ingredientResponse));
    }

    @Test
    void deleteRemovesIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        when(ingredientRepository.existsById(1L)).thenReturn(true);

        ingredientService.deleteById(1L);
    }

    @Test
    void deleteThrowsExceptionWhenIngredientDoesNotExist() {
        when(ingredientRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> ingredientService.deleteById(1L));
    }
}

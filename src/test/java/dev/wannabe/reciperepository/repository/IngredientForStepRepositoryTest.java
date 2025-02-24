package dev.wannabe.reciperepository.repository;


import dev.wannabe.reciperepository.model.*;
import dev.wannabe.reciperepository.model.types.IngredientType;
import dev.wannabe.reciperepository.model.types.RecipeType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class IngredientForStepRepositoryTest {

    @Autowired
    private IngredientForStepRepository ingredientForStepRepository;

    @Autowired
    private RecipeProcessRepository recipeProcessRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    private Ingredient ingredient;
    private RecipeProcess recipeProcess;
    private IngredientForStep ingredientForStep;



    @BeforeEach
    void setUp() {

        Supplier supplier = supplierRepository.save(new Supplier("Supplier", "type", "address"));

        ingredient = ingredientRepository.save(new Ingredient("Tomato",new Date(),10.0, IngredientType.INGREDIENT, "kg", supplier));

        Recipe recipe = recipeRepository.save(new Recipe("Recipe", "Description", RecipeType.UNDEFINED));

        recipeProcess = recipeProcessRepository.save(new RecipeProcess(recipe,1,"Step", "Description",60, "Piec","Human"));

        ingredientForStep = ingredientForStepRepository.save(new IngredientForStep(ingredient, recipeProcess, 1.0));
    }

    @AfterEach
    void tearDown() {
        ingredientForStepRepository.deleteAll();
        recipeProcessRepository.deleteAll();
        ingredientRepository.deleteAll();
    }

    @Test
    void findByIdShouldReturnIngredientForStepWhenIdExists() {
        Optional<IngredientForStep> foundIngredientForStep = ingredientForStepRepository.findById(ingredientForStep.getId());

        assertTrue(foundIngredientForStep.isPresent());
        assertEquals(ingredientForStep.getId(), foundIngredientForStep.get().getId());

    }

    @Test
    void findAllShouldReturnEmptyListWhenNoIngredientsForStep() {
        ingredientForStepRepository.deleteAll();
        assertTrue(ingredientForStepRepository.findAll().isEmpty());

    }



    @Test
    void saveShouldPersistIngredientForStep() {


        assertNotNull(ingredientForStep.getId());
        assertEquals(ingredient.getId(), ingredientForStep.getIngredient().getId());
        assertEquals(recipeProcess.getId(), ingredientForStep.getRecipeProcess().getId());
        assertEquals(1.0, ingredientForStep.getQuantityNeeded());

    }

    @Test
    void deleteByIdShouldRemoveIngredientForStep() {
        ingredientForStepRepository.deleteById(ingredientForStep.getId());
        Optional<IngredientForStep> foundIngredientForStep = ingredientForStepRepository.findById(ingredientForStep.getId());

        assertFalse(foundIngredientForStep.isPresent());

    }

    @Test
    void updateShouldUpdateIngredientForStep() {
        ingredientForStep.setQuantityNeeded(2.0);
        ingredientForStepRepository.save(ingredientForStep);
        Optional<IngredientForStep> foundIngredientForStep = ingredientForStepRepository.findById(ingredientForStep.getId());

        assertTrue(foundIngredientForStep.isPresent());
        assertEquals(2.0, foundIngredientForStep.get().getQuantityNeeded());
    }



}

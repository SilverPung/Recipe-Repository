package dev.wannabe.reciperepository.repository;


import dev.wannabe.reciperepository.model.Ingredient;
import dev.wannabe.reciperepository.model.Supplier;
import dev.wannabe.reciperepository.model.types.IngredientType;
import dev.wannabe.reciperepository.model.types.SupplierType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        Supplier supplier = new Supplier("Supplier", SupplierType.FIRM, "address");
        supplier = supplierRepository.save(supplier);
        ingredient = new Ingredient("Tomato", new Date(), 10.0, IngredientType.INGREDIENT, "kg", supplier);
        ingredient = ingredientRepository.save(ingredient);
    }


    @Test
    void findByIdShouldReturnIngredientWhenIdExists() {
        Optional<Ingredient> foundIngredient = ingredientRepository.findById(ingredient.getId());

        assertTrue(foundIngredient.isPresent());
        assertEquals(ingredient.getId(), foundIngredient.get().getId());
    }

    @Test
    void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExist() {
        Optional<Ingredient> foundIngredient = ingredientRepository.findById(999L);

        assertFalse(foundIngredient.isPresent());
    }

    @Test
    void saveShouldPersistIngredient() {
        Ingredient savedIngredient = ingredientRepository.save(ingredient);

        assertNotNull(savedIngredient.getId());
        assertEquals("Tomato", savedIngredient.getName());
    }

    @Test
    void deleteByIdShouldRemoveIngredient() {
        ingredientRepository.deleteById(ingredient.getId());
        Optional<Ingredient> foundIngredient = ingredientRepository.findById(ingredient.getId());

        assertFalse(foundIngredient.isPresent());
    }

    @Test
    void updateShouldUpdateIngredient() {
        ingredient.setName("Updated Ingredient");
        ingredientRepository.save(ingredient);
        Optional<Ingredient> foundIngredient = ingredientRepository.findById(ingredient.getId());

        assertTrue(foundIngredient.isPresent());
        assertEquals("Updated Ingredient", foundIngredient.get().getName());
    }


}

package dev.wannabe.reciperepository.repository;


import dev.wannabe.reciperepository.model.Recipe;
import dev.wannabe.reciperepository.model.RecipeProcess;
import dev.wannabe.reciperepository.model.types.RecipeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RecipeProcessRepositoryTest {


    @Autowired
    private RecipeProcessRepository recipeProcessRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    private RecipeProcess savedRecipeProcess;

    @BeforeEach
    void setUp() {
        Recipe recipe = recipeRepository.save(new Recipe("Test Recipe", "Test Description", RecipeType.UNDEFINED));
        savedRecipeProcess = recipeProcessRepository.save(
                new RecipeProcess(
                        recipe, 1,"name", "description", 10,"unit","human"
                    ));
    }

    @Test
    void findByIdShouldReturnRecipeProcessWhenIdExists() {
        Optional<RecipeProcess> foundRecipeProcess = recipeProcessRepository.findById(savedRecipeProcess.getId());

        assertTrue(foundRecipeProcess.isPresent());
        assertEquals("name", foundRecipeProcess.get().getName());

    }

    @Test
    void findAllShouldReturnEmptyListWhenNoRecipeProcesses() {
        recipeProcessRepository.deleteAll();
        Iterable<RecipeProcess> foundRecipeProcesses = recipeProcessRepository.findAll();

        assertFalse(foundRecipeProcesses.iterator().hasNext());
    }

    @Test
    void saveShouldPersistRecipeProcess() {

        assertNotNull(savedRecipeProcess.getId());
        assertEquals("name", savedRecipeProcess.getName());
    }

    @Test
    void deleteByIdShouldRemoveRecipeProcess() {

        recipeProcessRepository.deleteById(savedRecipeProcess.getId());

        Optional<RecipeProcess> foundRecipeProcess = recipeProcessRepository.findById(savedRecipeProcess.getId());

        assertFalse(foundRecipeProcess.isPresent());
    }
    @Test
    void updateShouldUpdateRecipeProcess() {

        savedRecipeProcess.setName("Updated Recipe Process");
        recipeProcessRepository.save(savedRecipeProcess);

        Optional<RecipeProcess> foundRecipeProcess = recipeProcessRepository.findById(savedRecipeProcess.getId());

        assertTrue(foundRecipeProcess.isPresent());
        assertEquals("Updated Recipe Process", foundRecipeProcess.get().getName());
    }


}

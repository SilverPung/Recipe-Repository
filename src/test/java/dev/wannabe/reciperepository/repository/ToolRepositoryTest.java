package dev.wannabe.reciperepository.repository;

import dev.wannabe.reciperepository.model.Tool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ToolRepositoryTest {

    @Autowired
    private ToolRepository toolRepository;


    private Tool savedTool;

    @BeforeEach
    void setUp() {
       savedTool = toolRepository.save(new Tool("Test Tool", "Test Description"));
    }

    @Test
    void findByIdShouldReturnToolWhenIdExists() {


        Optional<Tool> foundTool = toolRepository.findById(savedTool.getId());

        assertTrue(foundTool.isPresent());
        assertEquals("Test Tool", foundTool.get().getName());
    }

    @Test
    void findAllShouldReturnEmptyListWhenNoTools() {
        toolRepository.deleteAll();
        Iterable<Tool> foundTools = toolRepository.findAll();

        assertFalse(foundTools.iterator().hasNext());
    }

    @Test
    void saveShouldPersistTool() {


        assertNotNull(savedTool.getId());
        assertEquals("Test Tool", savedTool.getName());
    }

    @Test
    void deleteByIdShouldRemoveTool() {


        toolRepository.deleteById(1L);
        Optional<Tool> foundTool = toolRepository.findById(1L);

        assertFalse(foundTool.isPresent());
    }

    @Test
    void updateShouldUpdateTool() {

        savedTool.setName("Updated Tool");
        toolRepository.save(savedTool);
        Optional<Tool> foundTool = toolRepository.findById(savedTool.getId());

        assertTrue(foundTool.isPresent());
        assertEquals("Updated Tool", foundTool.get().getName());
    }
}
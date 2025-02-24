package dev.wannabe.reciperepository.service;

import dev.wannabe.reciperepository.exception.UniqueForeignKeyException;
import dev.wannabe.reciperepository.model.Recipe;
import dev.wannabe.reciperepository.model.RecipeProcess;
import dev.wannabe.reciperepository.model.Tool;
import dev.wannabe.reciperepository.model.request.RecipeProcessRequest;
import dev.wannabe.reciperepository.repository.RecipeProcessRepository;
import dev.wannabe.reciperepository.repository.RecipeRepository;
import dev.wannabe.reciperepository.repository.ToolRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RecipeProcessServiceTest {

    @Mock
    private RecipeProcessRepository recipeProcessRepository;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private ToolRepository toolRepository;

    @InjectMocks
    private RecipeProcessService recipeProcessService;

    @Test
    void findAllReturnsListOfRecipeProcesses() {
        List<RecipeProcess> recipeProcesses = List.of(new RecipeProcess(), new RecipeProcess());
        when(recipeProcessRepository.findAll()).thenReturn(recipeProcesses);

        List<RecipeProcess> result = recipeProcessService.findAll();

        assertEquals(recipeProcesses, result);
    }

    @Test
    void findAllReturnsEmptyListWhenNoRecipeProcesses() {
        when(recipeProcessRepository.findAll()).thenReturn(List.of());

        List<RecipeProcess> result = recipeProcessService.findAll();

        assertEquals(0, result.size());
    }

    @Test
    void findByIdReturnsRecipeProcessWhenExists() {
        RecipeProcess recipeProcess = new RecipeProcess();
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.of(recipeProcess));

        RecipeProcess result = recipeProcessService.findById(1L);

        assertEquals(recipeProcess, result);
    }

    @Test
    void findByIdThrowsExceptionWhenRecipeProcessDoesNotExist() {
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> recipeProcessService.findById(1L));
    }

    @Test
    void savePersistsRecipeProcess() {
        RecipeProcessRequest recipeProcessRequest = new RecipeProcessRequest(1L, 1, "name", "description", 60 , "bench", "human");
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(new Recipe()));
        RecipeProcess recipeProcess = new RecipeProcess();
        when(recipeProcessRepository.save(any(RecipeProcess.class))).thenReturn(recipeProcess);

        RecipeProcess result = recipeProcessService.save(recipeProcessRequest);

        assertEquals(recipeProcess, result);
    }

    @Test
    void saveThrowsExceptionWhenRecipeDoesNotExist() {
        RecipeProcessRequest recipeProcessRequest = new RecipeProcessRequest(1L, 1, "name", "description", 60 , "bench", "human");
        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> recipeProcessService.save(recipeProcessRequest));
    }
    @Test
    void saveThrowsExceptionWhenUniqueConstraintViolation() {
        RecipeProcessRequest recipeProcessRequest = new RecipeProcessRequest(1L, 1, "name", "description", 60 , "bench", "human");
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(new Recipe()));
        when(recipeProcessRepository.save(any(RecipeProcess.class))).thenThrow(new DataIntegrityViolationException("Unique index or primary key violation"));

        assertThrows(UniqueForeignKeyException.class, () -> recipeProcessService.save(recipeProcessRequest));
    }

    @Test
    void updateReturnsUpdatedRecipeProcess() {
        RecipeProcess recipeProcess = new RecipeProcess();
        recipeProcess.setId(1L);
        RecipeProcessRequest recipeProcessRequest = new RecipeProcessRequest(1L, 1, "name", "description", 60 , "bench", "human");
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(new Recipe()));
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.of(recipeProcess));
        when(recipeProcessRepository.save(any(RecipeProcess.class))).thenReturn(recipeProcess);

        RecipeProcess result = recipeProcessService.update(1L, recipeProcessRequest);

        assertEquals(recipeProcess, result);
    }

    @Test
    void updateThrowsExceptionWhenRecipeProcessDoesNotExist() {
        RecipeProcess recipeProcess = new RecipeProcess();
        recipeProcess.setId(1L);
        RecipeProcessRequest recipeProcessRequest = new RecipeProcessRequest(1L, 1, "name", "description", 60 , "bench", "human");
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> recipeProcessService.update(1L, recipeProcessRequest));
    }

    @Test
    void updateThrowsExceptionWhenUniqueConstraintViolation() {
        RecipeProcess recipeProcess = new RecipeProcess();
        recipeProcess.setId(1L);
        RecipeProcessRequest recipeProcessRequest = new RecipeProcessRequest(1L, 1, "name", "description", 60 , "bench", "human");
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(new Recipe()));
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.of(recipeProcess));
        when(recipeProcessRepository.save(any(RecipeProcess.class))).thenThrow(new DataIntegrityViolationException("Unique index or primary key violation"));

        assertThrows(UniqueForeignKeyException.class, () -> recipeProcessService.update(1L, recipeProcessRequest));
    }

    @Test
    void deleteRemovesRecipeProcess() {
        RecipeProcess recipeProcess = new RecipeProcess();
        recipeProcess.setId(1L);
        when(recipeProcessRepository.existsById(1L)).thenReturn(true);

        recipeProcessService.deleteById(1L);
    }

    @Test
    void deleteThrowsExceptionWhenRecipeProcessDoesNotExist() {
        when(recipeProcessRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> recipeProcessService.deleteById(1L));
    }

    @Test
    void addToolAddsToolToRecipeProcess() {
        RecipeProcess recipeProcess = new RecipeProcess();
        Tool tool = new Tool();
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.of(recipeProcess));
        when(toolRepository.findById(1L)).thenReturn(Optional.of(tool));
        when(recipeProcessRepository.save(any(RecipeProcess.class))).thenReturn(recipeProcess);

        RecipeProcess result = recipeProcessService.addTool(1L, 1L);

        assertEquals(recipeProcess, result);
    }

    @Test
    void addToolThrowsExceptionWhenRecipeProcessDoesNotExist() {
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> recipeProcessService.addTool(1L, 1L));
    }

    @Test
    void addToolThrowsExceptionWhenToolDoesNotExist() {
        RecipeProcess recipeProcess = new RecipeProcess();
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.of(recipeProcess));
        when(toolRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> recipeProcessService.addTool(1L, 1L));
    }

    @Test
    void removeToolRemovesToolFromRecipeProcess() {
        RecipeProcess recipeProcess = new RecipeProcess();
        Tool tool = new Tool();
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.of(recipeProcess));
        when(toolRepository.findById(1L)).thenReturn(Optional.of(tool));

        recipeProcessService.removeTool(1L, 1L);
    }

    @Test
    void removeToolThrowsExceptionWhenRecipeProcessDoesNotExist() {
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> recipeProcessService.removeTool(1L, 1L));
    }

    @Test
    void removeToolThrowsExceptionWhenToolDoesNotExist() {
        RecipeProcess recipeProcess = new RecipeProcess();
        when(recipeProcessRepository.findById(1L)).thenReturn(Optional.of(recipeProcess));
        when(toolRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> recipeProcessService.removeTool(1L, 1L));
    }
}
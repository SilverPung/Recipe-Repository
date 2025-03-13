package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.model.Tool;
import dev.wannabe.reciperepository.repository.ToolRepository;
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
public class ToolServiceTest {

    @Mock
    private ToolRepository toolRepository;

    @InjectMocks
    private ToolService toolService;

    @Test
    public void findAllReturnsListOfTools() {
        List<Tool> tools = List.of(new Tool(), new Tool());
        when(toolRepository.findAll()).thenReturn(tools);

        List<Tool> result = toolService.findAll();

        assertEquals(tools, result);
    }

    @Test
    public void findAllReturnsEmptyList() {
        when(toolRepository.findAll()).thenReturn(List.of());

        List<Tool> result = toolService.findAll();

        assertEquals(0, result.size());
    }

    @Test
    public void findByIdReturnsTool() {
        Tool tool = new Tool();
        when(toolRepository.findById(1L)).thenReturn(Optional.of(tool));

        Tool result = toolService.findById(1L);

        assertEquals(tool, result);
    }

    @Test
    public void findByIdThrowsEntityNotFoundException() {
        when(toolRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> toolService.findById(1L));
    }

    @Test
    public void saveReturnsTool() {
        Tool tool = new Tool();
        when(toolRepository.save(tool)).thenReturn(tool);

        Tool result = toolService.save(tool);

        assertEquals(tool, result);
    }

    @Test
    public void updateReturnsTool() {
        Tool tool = new Tool();
        tool.setId(1L);
        when(toolRepository.existsById(1L)).thenReturn(true);
        when(toolRepository.save(tool)).thenReturn(tool);

        Tool result = toolService.update(tool);

        assertEquals(tool, result);
    }

    @Test
    public void updateThrowsEntityNotFoundException() {
        Tool tool = new Tool();
        tool.setId(1L);
        when(toolRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> toolService.update(tool));
    }

    @Test
    public void deleteByIdDeletesTool() {
        when(toolRepository.existsById(1L)).thenReturn(true);
        Mockito.doNothing().when(toolRepository).deleteById(1L);

        assertDoesNotThrow(() -> toolService.deleteById(1L));
    }

    @Test
    public void deleteByIdThrowsEntityNotFoundException() {
        when(toolRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> toolService.deleteById(1L));
    }
}

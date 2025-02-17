package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.model.Tool;
import dev.wannabe.reciperepository.repository.ToolRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService {

    private final ToolRepository toolRepository;

    @Autowired
    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public Tool save(Tool tool) {
        return toolRepository.save(tool);
    }

    public Tool update(Tool tool) {
        if(!toolRepository.existsById(tool.getId())){
            throw new EntityNotFoundException("Tool on id " + tool.getId() + " not found");
        }
        return toolRepository.save(tool);
    }

    public Tool findById(Long id) {
        return toolRepository.findById(id).orElse(null);
    }

    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    public void deleteById(Long id) {
        if(!toolRepository.existsById(id)){
            throw new EntityNotFoundException("Tool on id " + id + " not found");
        }
        toolRepository.deleteById(id);
    }
}

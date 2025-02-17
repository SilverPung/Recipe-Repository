package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.model.Tool;
import dev.wannabe.reciperepository.repository.ToolRepository;
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

    public Tool findById(Long id) {
        return toolRepository.findById(id).orElse(null);
    }

    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    public void deleteById(Long id) {
        toolRepository.deleteById(id);
    }
}

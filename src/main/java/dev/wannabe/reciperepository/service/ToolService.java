package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.model.Tool;
import dev.wannabe.reciperepository.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Tool getToolById(Long id) {
        return toolRepository.findById(id).orElse(null);
    }

    public Iterable<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    public long deleteTool(Long id) {
        boolean exists = toolRepository.existsById(id);
        if (exists) {
            toolRepository.deleteById(id);
            return id;
        }
        return -1;
    }
}

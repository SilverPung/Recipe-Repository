package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.wannabe.reciperepository.model.Tool;

import java.util.List;



@RequestMapping("/api/tools")
@RestController
public class ToolController {

    private final ToolService toolService;

    @Autowired
    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }


    @GetMapping("")
    public ResponseEntity<List<Tool>> getTools() {
        return ResponseEntity.ok(toolService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tool> getToolById(@PathVariable Long id) {
        return ResponseEntity.ok(toolService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<Tool> createTool(@RequestBody Tool tool) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toolService.save(tool));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tool> updateTool(@PathVariable Long id, @RequestBody Tool tool) {
        tool.setId(id);
        return ResponseEntity.ok(toolService.save(tool));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTool(@PathVariable Long id) {
        toolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.wannabe.reciperepository.model.Tool;

import java.util.List;
import java.util.stream.Stream;


@RequestMapping("/api")
@RestController
public class ToolController {

    private final ToolService toolService;

    @Autowired
    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }


    @GetMapping("/tools")
    public ResponseEntity<List<Tool>> getTools() {
        return new ResponseEntity<>(toolService.getAllTools(), HttpStatus.OK);
    }

    @GetMapping("/tools/{id}")
    public ResponseEntity<Tool> getToolById(@PathVariable Long id) {
        return Stream.of(toolService.findById(id))
                .map(tool -> new ResponseEntity<>(tool, HttpStatus.OK))
                .findFirst()
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/tools")
    public ResponseEntity<Tool> createTool(@RequestBody Tool tool) {
        return new ResponseEntity<>(toolService.save(tool), HttpStatus.CREATED);
    }

    @PutMapping("/tools/{id}")
    public ResponseEntity<Tool> updateTool(@PathVariable Long id, @RequestBody Tool tool) {
        tool.setId(id);
        return new ResponseEntity<>(toolService.update(tool), HttpStatus.OK);
    }

    @DeleteMapping("/tools/{id}")
    public ResponseEntity<Void> deleteTool(@PathVariable Long id) {
        toolService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.wannabe.reciperepository.model.Tool;


@RequestMapping("/api")
@RestController
public class ToolController {

    private final ToolService toolService;

    @Autowired
    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }


    @GetMapping("/tools")
    public ResponseEntity<Iterable<Tool>> getTools() {
        Iterable<Tool> tools = toolService.getAllTools();
        return new ResponseEntity<>(tools, HttpStatus.OK);
    }

    @GetMapping("/tools/{id}")
    public ResponseEntity<Tool> getToolById(@PathVariable Long id) {
        Tool tool = toolService.getToolById(id);
        if (tool != null) {
            return new ResponseEntity<>(tool, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tools")
    public ResponseEntity<Tool> createTool(@RequestBody Tool tool) {
        Tool newTool = toolService.save(tool);
        return new ResponseEntity<>(newTool, HttpStatus.CREATED);
    }

    @PutMapping("/tools/{id}")
    public ResponseEntity<Tool> updateTool(@PathVariable Long id, @RequestBody Tool tool) {
        tool.setId(id);
        Tool updatedTool = toolService.save(tool);

        if (updatedTool != null) {
            return new ResponseEntity<>(updatedTool, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tools/{id}")
    public ResponseEntity<Long> deleteTool(@PathVariable Long id) {
        long deletedId = toolService.deleteTool(id);
        return new ResponseEntity<>(deletedId, HttpStatus.NO_CONTENT);
    }


}

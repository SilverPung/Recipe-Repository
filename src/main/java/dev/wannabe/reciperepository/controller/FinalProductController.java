package dev.wannabe.reciperepository.controller;

import dev.wannabe.reciperepository.model.FinalProduct;
import dev.wannabe.reciperepository.service.FinalProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api")
public class FinalProductController {

    private final FinalProductService finalProductService;

    @Autowired
    public FinalProductController(FinalProductService finalProductService) {
        this.finalProductService = finalProductService;
    }

    @GetMapping("/final-products")
    public ResponseEntity<List<FinalProduct>> getFinalProducts() {
        return ResponseEntity.ok(finalProductService.findAll());
    }

    @GetMapping("/final-products/{id}")
    public ResponseEntity<FinalProduct> getFinalProductById(@PathVariable Long id) {
       return ResponseEntity.ok(finalProductService.findById(id));
    }

    @PostMapping("/final-products")
    public ResponseEntity<FinalProduct> saveFinalProduct(@RequestBody FinalProduct finalProduct) {
        return ResponseEntity.status(HttpStatus.CREATED).body(finalProductService.save(finalProduct));
    }

    @DeleteMapping("/final-products/{id}")
    public ResponseEntity<Void> deleteFinalProduct(@PathVariable Long id) {
        finalProductService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/final-products/{id}")
    public ResponseEntity<FinalProduct> updateFinalProduct(@PathVariable Long id, @RequestBody FinalProduct finalProduct) {
        finalProduct.setId(id);
        return ResponseEntity.ok(finalProductService.save(finalProduct));
    }
}
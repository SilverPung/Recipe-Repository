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
        List<FinalProduct> products = finalProductService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/final-products/{id}")
    public ResponseEntity<FinalProduct> getFinalProductById(@PathVariable Long id) {
        FinalProduct product = finalProductService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/final-products")
    public ResponseEntity<FinalProduct> saveFinalProduct(@RequestBody FinalProduct finalProduct) {
        FinalProduct savedProduct = finalProductService.save(finalProduct);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/final-products/{id}")
    public ResponseEntity<Long> deleteFinalProduct(@PathVariable Long id) {
        long deletedId = finalProductService.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/final-products/{id}")
    public ResponseEntity<FinalProduct> updateFinalProduct(@PathVariable Long id, @RequestBody FinalProduct finalProduct) {
        finalProduct.setId(id);
        FinalProduct updatedProduct = finalProductService.save(finalProduct);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
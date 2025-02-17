package dev.wannabe.reciperepository.controller;

import dev.wannabe.reciperepository.model.FinalProduct;
import dev.wannabe.reciperepository.service.FinalProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;


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
        return new ResponseEntity<>(finalProductService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/final-products/{id}")
    public ResponseEntity<FinalProduct> getFinalProductById(@PathVariable Long id) {
       return Stream.of(finalProductService.findById(id))
               .map(finalProduct -> new ResponseEntity<>(finalProduct, HttpStatus.OK))
               .findFirst()
               .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/final-products")
    public ResponseEntity<FinalProduct> saveFinalProduct(@RequestBody FinalProduct finalProduct) {
        return new ResponseEntity<>(finalProductService.save(finalProduct), HttpStatus.CREATED);
    }

    @DeleteMapping("/final-products/{id}")
    public ResponseEntity<Void> deleteFinalProduct(@PathVariable Long id) {
        finalProductService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/final-products/{id}")
    public ResponseEntity<FinalProduct> updateFinalProduct(@PathVariable Long id, @RequestBody FinalProduct finalProduct) {
        finalProduct.setId(id);
        return new ResponseEntity<>(finalProductService.update(finalProduct), HttpStatus.OK);
    }
}
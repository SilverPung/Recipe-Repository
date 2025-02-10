package dev.wannabe.reciperepository.controller;


import dev.wannabe.reciperepository.model.FinalProduct;
import dev.wannabe.reciperepository.service.FinalProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FinalProductController {

    private final FinalProductService finalProductService;

    @Autowired
    public FinalProductController(FinalProductService finalProductService) {
        this.finalProductService = finalProductService;
    }

    @GetMapping("/final-products")
    public Iterable<FinalProduct> getFinalProducts() {
        return finalProductService.findAll();
    }

    @GetMapping("/final-products/{id}")
    public FinalProduct getFinalProductById(@PathVariable Long id) {
        return finalProductService.findById(id);
    }

    @PostMapping("/final-products")
    public FinalProduct saveFinalProduct(@RequestBody FinalProduct finalProduct) {
        return finalProductService.save(finalProduct);
    }

    @DeleteMapping("/final-products/{id}")
    public void deleteFinalProduct(@PathVariable Long id) {
        finalProductService.deleteById(id);
    }

    @PutMapping("/final-products/{id}")
    public FinalProduct updateFinalProduct(@PathVariable Long id, @RequestBody FinalProduct finalProduct) {
        finalProduct.setId(id);
        return finalProductService.save(finalProduct);
    }

}

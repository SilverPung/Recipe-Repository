package dev.wannabe.reciperepository.controller;

import dev.wannabe.reciperepository.model.FinalProduct;
import dev.wannabe.reciperepository.model.request.PageResult;
import dev.wannabe.reciperepository.service.FinalProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/final-products")
public class FinalProductController {

    private final FinalProductService finalProductService;

    @Autowired
    public FinalProductController( FinalProductService finalProductService) {
        this.finalProductService = finalProductService;
    }

    @GetMapping("")
    public ResponseEntity<PageResult<FinalProduct>> getFinalProducts(@PageableDefault(sort="name",direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(finalProductService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinalProduct> getFinalProductById(@PathVariable Long id) {
       return ResponseEntity.ok(finalProductService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<FinalProduct> saveFinalProduct(@RequestBody FinalProduct finalProduct) {
        return ResponseEntity.status(HttpStatus.CREATED).body(finalProductService.save(finalProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinalProduct(@PathVariable Long id) {
        finalProductService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinalProduct> updateFinalProduct(@PathVariable Long id, @RequestBody FinalProduct finalProduct) {
        finalProduct.setId(id);
        return ResponseEntity.ok(finalProductService.save(finalProduct));
    }

}
package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.model.Ingredient;
import dev.wannabe.reciperepository.model.request.IngredientResponse;
import dev.wannabe.reciperepository.repository.IngredientRepository;
import dev.wannabe.reciperepository.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, SupplierRepository supplierRepository) {
        this.ingredientRepository = ingredientRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public Ingredient save(IngredientResponse ingredientResponse) {
        Ingredient ingredient = new Ingredient();
        ingredient.setSupplier(supplierRepository.findById(ingredientResponse.getSupplierId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found")
        ));
        ingredient.setData(ingredientResponse);
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Long id, IngredientResponse ingredientResponse) {
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found")
        );
        ingredient.setSupplier(supplierRepository.findById(ingredientResponse.getSupplierId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found")
        ));
        ingredient.setData(ingredientResponse);
        return ingredientRepository.save(ingredient);
    }

    public long deleteById(Long id) {
        boolean exists = ingredientRepository.existsById(id);
        if (exists) {
            ingredientRepository.deleteById(id);
            return id;
        }
        return -1;
    }
}

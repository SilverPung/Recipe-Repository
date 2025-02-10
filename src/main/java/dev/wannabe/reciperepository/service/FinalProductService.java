package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.model.FinalProduct;
import dev.wannabe.reciperepository.repository.FinalProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalProductService {

    private final FinalProductRepository finalProductRepository;

    @Autowired
    public FinalProductService(FinalProductRepository finalProductRepository) {
        this.finalProductRepository = finalProductRepository;
    }

    public void deleteById(Long id) {
        finalProductRepository.deleteById(id);
    }

    public FinalProduct save(FinalProduct finalProduct) {
        return finalProductRepository.save(finalProduct);
    }

    public FinalProduct findById(Long id) {
        return finalProductRepository.findById(id).orElse(null);
    }

    public Iterable<FinalProduct> findAll() {
        return finalProductRepository.findAll();
    }







}

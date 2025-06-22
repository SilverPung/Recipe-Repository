package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.model.request.PageResult;
import jakarta.persistence.EntityNotFoundException;
import dev.wannabe.reciperepository.model.FinalProduct;
import dev.wannabe.reciperepository.repository.FinalProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalProductService {

    private final FinalProductRepository finalProductRepository;

    @Autowired
    public FinalProductService(FinalProductRepository finalProductRepository) {
        this.finalProductRepository = finalProductRepository;
    }



    public PageResult<FinalProduct> findAll(Pageable pageable) {
        return PageResult.fromPage(finalProductRepository.findAll(pageable));
    }

    public FinalProduct findById(Long id) {
        return finalProductRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("FinalProduct on id " + id + " not found"));
    }

    public FinalProduct save(FinalProduct finalProduct) {
        return finalProductRepository.save(finalProduct);
    }
    public FinalProduct update(FinalProduct finalProduct) {
        if(!finalProductRepository.existsById(finalProduct.getId())){
            throw new EntityNotFoundException("FinalProduct on id " + finalProduct.getId() + " not found");
        }
        return finalProductRepository.save(finalProduct);
    }

    public void deleteById(Long id) {

        if(!finalProductRepository.existsById(id)){
            throw new EntityNotFoundException("FinalProduct on id " + id + " not found");
        }

        finalProductRepository.deleteById(id);
    }

}

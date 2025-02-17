package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.model.Supplier;
import dev.wannabe.reciperepository.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }



    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier update(Supplier supplier) {
        if(!supplierRepository.existsById(supplier.getId())){
            throw new EntityNotFoundException("Supplier on id " + supplier.getId() + " not found");
        }
        return supplierRepository.save(supplier);
    }

    public void deleteById(Long id) {
        if(!supplierRepository.existsById(id)){
            throw new EntityNotFoundException("Supplier on id " + id + " not found");
        }
        supplierRepository.deleteById(id);
    }
}

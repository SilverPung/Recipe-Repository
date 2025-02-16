package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.model.Supplier;
import dev.wannabe.reciperepository.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }



    public Iterable<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public long deleteById(Long id) {
        boolean exists = supplierRepository.existsById(id);
        if (exists) {
            supplierRepository.deleteById(id);
            return id;
        } else {
            return -1;
        }
    }
}

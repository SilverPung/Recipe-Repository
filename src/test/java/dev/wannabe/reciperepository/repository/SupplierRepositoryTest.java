package dev.wannabe.reciperepository.repository;

import dev.wannabe.reciperepository.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SupplierRepositoryTest {

    @Autowired
    private SupplierRepository supplierRepository;

    private Supplier savedSupplier;

    @BeforeEach
    void setUp() {
        savedSupplier = supplierRepository.save(new Supplier("Test Supplier", "Test Type", "test@example.com"));
    }

    @Test
    void findByIdShouldReturnSupplierWhenIdExists() {
        Optional<Supplier> foundSupplier = supplierRepository.findById(savedSupplier.getId());

        assertTrue(foundSupplier.isPresent());
        assertEquals("Test Supplier", foundSupplier.get().getName());
    }

    @Test
    void findAllShouldReturnEmptyListWhenNoSuppliers() {
        supplierRepository.deleteAll();
        Iterable<Supplier> foundSuppliers = supplierRepository.findAll();

        assertFalse(foundSuppliers.iterator().hasNext());
    }

    @Test
    void saveShouldPersistSupplier() {
        assertNotNull(savedSupplier.getId());
        assertEquals("Test Supplier", savedSupplier.getName());
    }

    @Test
    void deleteByIdShouldRemoveSupplier() {
        supplierRepository.deleteById(savedSupplier.getId());
        Optional<Supplier> foundSupplier = supplierRepository.findById(savedSupplier.getId());

        assertFalse(foundSupplier.isPresent());
    }

    @Test
    void updateShouldUpdateSupplier() {
        savedSupplier.setName("Updated Supplier");
        supplierRepository.save(savedSupplier);
        Optional<Supplier> foundSupplier = supplierRepository.findById(savedSupplier.getId());

        assertTrue(foundSupplier.isPresent());
        assertEquals("Updated Supplier", foundSupplier.get().getName());
    }
}
package dev.wannabe.reciperepository.service;


import dev.wannabe.reciperepository.model.Supplier;
import dev.wannabe.reciperepository.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;



@SpringBootTest
public class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;




    @Test
    void findAllReturnsListOfSuppliers() {
        List<Supplier> suppliers = List.of(new Supplier(), new Supplier());
        when(supplierRepository.findAll()).thenReturn(suppliers);

        List<Supplier> result = supplierService.findAll();

        assertEquals(suppliers, result);
    }

    @Test
    void findAllReturnsEmptyListWhenNoSuppliers() {
        when(supplierRepository.findAll()).thenReturn(List.of());

        List<Supplier> result = supplierService.findAll();

        assertEquals(0, result.size());
    }

    @Test
    void findByIdReturnsSupplierWhenExists() {
        Supplier supplier = new Supplier();
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(supplier));

        Supplier result = supplierService.findById(1L);

        assertEquals(supplier, result);
    }

    @Test
    void findByIdThrowsExceptionWhenNotFound() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> supplierService.findById(1L));
    }

    @Test
    void savePersistsSupplier() {
        Supplier supplier = new Supplier();
        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        Supplier result = supplierService.save(supplier);

        assertEquals(supplier, result);
    }

    @Test
    void updateReturnsUpdatedSupplierWhenFound() {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        when(supplierRepository.existsById(anyLong())).thenReturn(true);
        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        Supplier result = supplierService.update(supplier);

        assertEquals(supplier, result);
    }

    @Test
    void updateThrowsExceptionWhenNotFound() {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        when(supplierRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> supplierService.update(supplier));
    }

    @Test
    void deleteByIdDeletesSupplierWhenFound() {
        when(supplierRepository.existsById(anyLong())).thenReturn(true);
        Mockito.doNothing().when(supplierRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> supplierService.deleteById(1L));
    }

    @Test
    void deleteByIdThrowsExceptionWhenNotFound() {
        when(supplierRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> supplierService.deleteById(1L));
    }
}




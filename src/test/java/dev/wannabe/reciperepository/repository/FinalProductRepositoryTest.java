package dev.wannabe.reciperepository.repository;

import dev.wannabe.reciperepository.model.FinalProduct;
import dev.wannabe.reciperepository.model.types.FinalProductType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class FinalProductRepositoryTest {

    @Autowired
    private FinalProductRepository finalProductRepository;

    @Test
    void findByIdShouldReturnFinalProductWhenIdExists() {
        // Given
        FinalProduct finalProduct = new FinalProduct(FinalProductType.FOR_SALE, "Pizza", "Pizza with cheese", "kg", 3,new Date());
        FinalProduct savedProduct = finalProductRepository.save(finalProduct);


        // When
        Optional<FinalProduct> foundProduct = finalProductRepository.findById(savedProduct.getId());

        // Then
        assertTrue(foundProduct.isPresent());
        assertEquals("Pizza", foundProduct.get().getName());
    }

    @Test
    void findAllShouldReturnEmptyListWhenNoProducts() {
        // When
        Iterable<FinalProduct> foundProducts = finalProductRepository.findAll();

        // Then
        assertFalse(foundProducts.iterator().hasNext());
    }

    @Test
    void saveShouldPersistFinalProduct() {
        // Given
        FinalProduct finalProduct = new FinalProduct(FinalProductType.FOR_SALE, "Pizza", "Pizza with cheese", "kg", 3,new Date());
        finalProduct.setName("Test Product");

        // When
        FinalProduct savedProduct = finalProductRepository.save(finalProduct);

        // Then
        assertNotNull(savedProduct.getId());
        assertEquals("Test Product", savedProduct.getName());
    }

    @Test
    void deleteByIdShouldRemoveFinalProduct() {
        // Given
        FinalProduct finalProduct = new FinalProduct(FinalProductType.FOR_SALE, "Pizza", "Pizza with cheese", "kg", 3,new Date());
        finalProductRepository.save(finalProduct);

        // When
        finalProductRepository.deleteById(1L);
        Optional<FinalProduct> foundProduct = finalProductRepository.findById(1L);

        // Then
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void updateShouldUpdateFinalProduct() {
        // Given
        FinalProduct finalProduct = new FinalProduct(FinalProductType.FOR_SALE, "Pizza", "Pizza with cheese", "kg", 3,new Date());
        FinalProduct savedProduct = finalProductRepository.save(finalProduct);

        // When
        savedProduct.setName("Updated Product");
        finalProductRepository.save(savedProduct);
        Optional<FinalProduct> foundProduct = finalProductRepository.findById(savedProduct.getId());

        // Then
        assertTrue(foundProduct.isPresent());
        assertEquals("Updated Product", foundProduct.get().getName());
    }




}
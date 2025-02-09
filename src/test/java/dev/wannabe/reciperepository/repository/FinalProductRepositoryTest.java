package dev.wannabe.reciperepository.repository;

import dev.wannabe.reciperepository.model.FinalProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FinalProductRepositoryTest {

    @Autowired
    private FinalProductRepository finalProductRepository;

    @Test
    public void testSave() {
        // given
        FinalProduct finalProduct = new FinalProduct();
        finalProduct.setName("Pizza");
        finalProduct.setDescription("Pizza with cheese");
        finalProduct.setType("Food");
        finalProduct.setMeasurementUnit("kg");
        finalProduct.setQuantity(3);
        finalProduct.setExpirationDate(new Date());

        // when
        finalProductRepository.save(finalProduct);

        // then
        FinalProduct savedProduct = finalProductRepository.findById(finalProduct.getId()).orElse(null);
        assertNotNull(savedProduct);
        assertEquals("Pizza", savedProduct.getName());
        assertEquals("Pizza with cheese", savedProduct.getDescription());
        assertEquals("Food", savedProduct.getType());
        assertEquals("kg", savedProduct.getMeasurementUnit());
        assertEquals(3, savedProduct.getQuantity());
    }

    @Test
    public void testUpdate() {
        // given
        FinalProduct finalProduct = new FinalProduct();
        finalProduct.setName("Pizza");
        finalProduct.setDescription("Pizza with cheese");
        finalProduct.setType("Food");
        finalProduct.setMeasurementUnit("kg");
        finalProduct.setQuantity(3);
        finalProduct.setExpirationDate(new Date());
        finalProductRepository.save(finalProduct);

        // when
        finalProduct.setName("Pizza with pepperoni");
        finalProductRepository.save(finalProduct);

        // then
        FinalProduct updatedProduct = finalProductRepository.findById(finalProduct.getId()).orElse(null);
        assertNotNull(updatedProduct);
        assertEquals("Pizza with pepperoni", updatedProduct.getName());


    }

    @Test
    public void testDelete() {
        // given
        FinalProduct finalProduct = new FinalProduct();
        finalProduct.setName("Pizza");
        finalProduct.setDescription("Pizza with cheese");
        finalProduct.setType("Food");
        finalProduct.setMeasurementUnit("kg");
        finalProduct.setQuantity(3);
        finalProduct.setExpirationDate(new Date());
        finalProductRepository.save(finalProduct);

        // when
        finalProductRepository.delete(finalProduct);

        // then
        FinalProduct deletedProduct = finalProductRepository.findById(finalProduct.getId()).orElse(null);
        assertNull(deletedProduct);
    }
}
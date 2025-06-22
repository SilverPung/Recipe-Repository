package dev.wannabe.reciperepository.service;



import dev.wannabe.reciperepository.model.FinalProduct;
import dev.wannabe.reciperepository.model.request.PageResult;
import dev.wannabe.reciperepository.model.types.FinalProductType;
import dev.wannabe.reciperepository.repository.FinalProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Optional;

@SpringBootTest
public class FinalProductServiceTest {

    @Mock
    private FinalProductRepository finalProductRepository;

    @InjectMocks
    private FinalProductService finalProductService;




    @Test
    public void findByIdReturnsProductWhenExists() {
        // given
        FinalProduct finalProduct = new FinalProduct(1, FinalProductType.FOR_SALE, "Pizza", "Pizza with cheese", "kg", 3, new Date());
        when(finalProductRepository.findById(1L)).thenReturn(Optional.of(finalProduct));

        // when
        FinalProduct foundProduct = finalProductService.findById(1L);

        // then
        assertEquals(finalProduct, foundProduct);
    }

    @Test
    public void findByIdThrowsExceptionWhenNotFound() {
        // given
        when(finalProductRepository.findById(1L)).thenReturn(Optional.empty());

        // when / then
        assertThrows(EntityNotFoundException.class, () -> finalProductService.findById(1L));
    }

    @Test
    public void savePersistsProduct() {
        // given
        FinalProduct finalProduct = new FinalProduct(1, FinalProductType.FOR_SALE, "Pizza", "Pizza with cheese", "kg", 3, new Date());
        when(finalProductRepository.save(finalProduct)).thenReturn(finalProduct);

        // when
        FinalProduct savedProduct = finalProductService.save(finalProduct);

        // then
        assertEquals(finalProduct, savedProduct);
    }

    @Test
    public void updateThrowsExceptionWhenProductNotFound() {
        // given
        FinalProduct finalProduct = new FinalProduct(1, FinalProductType.FOR_SALE, "Pizza", "Pizza with cheese", "kg", 3, new Date());
        when(finalProductRepository.existsById(1L)).thenReturn(false);

        // when / then
        assertThrows(EntityNotFoundException.class, () -> finalProductService.update(finalProduct));
    }
    @Test
    public void updateReturnsUpdatedProductWhenFound() {
        // given
        FinalProduct finalProduct = new FinalProduct(1, FinalProductType.FOR_SALE, "Pizza", "Pizza with cheese", "kg", 3, new Date());
        when(finalProductRepository.existsById(1L)).thenReturn(true);
        when(finalProductRepository.save(finalProduct)).thenReturn(finalProduct);

        // when
        FinalProduct updatedProduct = finalProductService.update(finalProduct);

        // then
        assertEquals(finalProduct, updatedProduct);
    }

    @Test
    public void deleteByIdThrowsExceptionWhenProductNotFound() {
        // given
        when(finalProductRepository.existsById(1L)).thenReturn(false);

        // when / then
        assertThrows(EntityNotFoundException.class, () -> finalProductService.deleteById(1L));
    }

    @Test
    public void deleteByIdDeletesProductWhenExists() {
        // given
        when(finalProductRepository.existsById(1L)).thenReturn(true);

        // when
        finalProductService.deleteById(1L);

        // then
        verify(finalProductRepository, times(1)).deleteById(1L);
    }

}

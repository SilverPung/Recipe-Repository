package dev.wannabe.reciperepository.controller;

import dev.wannabe.reciperepository.model.FinalProduct;
import dev.wannabe.reciperepository.service.FinalProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FinalProductControllerTest {

    @Mock
    private FinalProductService finalProductService;

    @InjectMocks
    private FinalProductController finalProductController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getFinalProductsReturnsListOfFinalProducts() {
        List<FinalProduct> finalProducts = Arrays.asList(new FinalProduct(), new FinalProduct());
        when(finalProductService.findAll()).thenReturn(finalProducts);

        ResponseEntity<List<FinalProduct>> response = finalProductController.getFinalProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(finalProducts, response.getBody());
    }

    @Test
    void getFinalProductByIdReturnsFinalProduct() {
        FinalProduct finalProduct = new FinalProduct();
        when(finalProductService.findById(1L)).thenReturn(finalProduct);

        ResponseEntity<FinalProduct> response = finalProductController.getFinalProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(finalProduct, response.getBody());
    }

    @Test
    void saveFinalProductReturnsCreatedFinalProduct() {
        FinalProduct finalProduct = new FinalProduct();
        when(finalProductService.save(any(FinalProduct.class))).thenReturn(finalProduct);

        ResponseEntity<FinalProduct> response = finalProductController.saveFinalProduct(finalProduct);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(finalProduct, response.getBody());
    }

    @Test
    void deleteFinalProductReturnsNoContent() {
        doNothing().when(finalProductService).deleteById(1L);

        ResponseEntity<Void> response = finalProductController.deleteFinalProduct(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void updateFinalProductReturnsUpdatedFinalProduct() {
        FinalProduct finalProduct = new FinalProduct();
        when(finalProductService.save(any(FinalProduct.class))).thenReturn(finalProduct);

        ResponseEntity<FinalProduct> response = finalProductController.updateFinalProduct(1L, finalProduct);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(finalProduct, response.getBody());
    }


}
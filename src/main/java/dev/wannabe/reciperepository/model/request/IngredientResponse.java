package dev.wannabe.reciperepository.model.request;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IngredientResponse {

    long supplierId;
    String name;
    Date expirationDate;
    Double quantity;
    String type;
    String measurementUnit;

    public IngredientResponse() {
    }

    public IngredientResponse(long supplierId, String name, Date expirationDate, Double quantity, String type, String measurementUnit) {
        this.supplierId = supplierId;
        this.name = name;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.type = type;
        this.measurementUnit = measurementUnit;
    }

}

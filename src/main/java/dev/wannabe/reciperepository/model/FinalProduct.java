package dev.wannabe.reciperepository.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class FinalProduct {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private String type;
    private String measurementUnit;
    private long quantity;
    private Date expirationDate;

    public FinalProduct() {
    }

    public FinalProduct(String name, String description, String type, String measurementUnit, long quantity, Date expirationDate) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.measurementUnit = measurementUnit;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }
}

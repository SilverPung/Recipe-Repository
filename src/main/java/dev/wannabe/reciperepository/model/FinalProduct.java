package dev.wannabe.reciperepository.model;


import dev.wannabe.reciperepository.model.specialenum.FinalProductType;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private FinalProductType type;

    private String name;
    private String description;
    private String measurementUnit;
    private double quantity;
    private Date expirationDate;

    public FinalProduct() {
    }

    public FinalProduct(FinalProductType type, String name, String description, String measurementUnit, double quantity, Date expirationDate) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.measurementUnit = measurementUnit;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public FinalProduct(long id, FinalProductType type, String name, String description, String measurementUnit, double quantity, Date expirationDate) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.measurementUnit = measurementUnit;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }


}

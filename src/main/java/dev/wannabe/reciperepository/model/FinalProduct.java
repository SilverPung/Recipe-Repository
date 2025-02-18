package dev.wannabe.reciperepository.model;


import dev.wannabe.reciperepository.model.types.FinalProductType;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class FinalProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private FinalProductType type;

    @NotBlank
    private String name;

    @NotNull
    private String description;

    @NotBlank
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

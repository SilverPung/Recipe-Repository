package dev.wannabe.reciperepository.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    private String name;
    private Date expirationDate;
    private long quantity;
    private String type;
    private String measurementUnit;

    @ManyToOne
    @JoinColumn(name="supplier_id", nullable=false)
    private Supplier supplier;

    public Ingredient() {
    }

    public Ingredient(String name, Date expirationDate, long quantity, String type, String measurementUnit, Supplier supplier) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.type = type;
        this.measurementUnit = measurementUnit;
        this.supplier = supplier;
    }

    public Ingredient(long id, String name, Date expirationDate, long quantity, String type, String measurementUnit, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.type = type;
        this.measurementUnit = measurementUnit;
        this.supplier = supplier;
    }
}

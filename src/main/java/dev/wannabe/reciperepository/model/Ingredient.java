package dev.wannabe.reciperepository.model;


import dev.wannabe.reciperepository.model.specialenum.IngredientType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    private String name;
    private Date expirationDate;
    private double quantity;
    private String measurementUnit;

    @Enumerated(EnumType.STRING)
    private IngredientType type;

    @ManyToOne
    @JoinColumn(name="supplier_id", nullable=false)
    private Supplier supplier;

    @OneToMany(mappedBy = "ingredient")
    private final Set<IngredientForStep> ingredientForSteps = new HashSet<>();

    public Ingredient() {
    }

    public Ingredient(String name, Date expirationDate, double quantity, IngredientType type, String measurementUnit, Supplier supplier) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.type = type;
        this.measurementUnit = measurementUnit;
        this.supplier = supplier;
    }

    public Ingredient(long id, String name, Date expirationDate, double quantity, IngredientType type, String measurementUnit, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.type = type;
        this.measurementUnit = measurementUnit;
        this.supplier = supplier;
    }
}

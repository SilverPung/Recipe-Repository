package dev.wannabe.reciperepository.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.wannabe.reciperepository.model.request.IngredientResponse;
import dev.wannabe.reciperepository.model.specialenum.IngredientType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;
    private Date expirationDate;
    private double quantity;

    @NotBlank
    private String measurementUnit;

    @Enumerated(EnumType.STRING)
    private IngredientType type;


    @JsonIgnoreProperties("ingredients")
    @ManyToOne
    @JoinColumn(name="supplier_id", nullable=false)
    private Supplier supplier;

    @JsonIgnoreProperties("ingredient")
    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.REMOVE)
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

    public void setData(IngredientResponse ingredientResponse) {
        this.name = ingredientResponse.getName();
        this.expirationDate = ingredientResponse.getExpirationDate();
        this.quantity = ingredientResponse.getQuantity();
        this.type = IngredientType.valueOf(ingredientResponse.getType());
        this.measurementUnit = ingredientResponse.getMeasurementUnit();
    }

}

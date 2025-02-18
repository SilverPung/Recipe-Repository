package dev.wannabe.reciperepository.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String email;


    @JsonIgnoreProperties("supplier")
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.REMOVE)
    private final Set<Ingredient> ingredients = new HashSet<>();

    public Supplier() {
    }

    public Supplier(String name, String type, String email) {
        this.name = name;
        this.type = type;
        this.email = email;
    }


    public Supplier(long id, String name, String type, String email) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.email = email;
    }


}

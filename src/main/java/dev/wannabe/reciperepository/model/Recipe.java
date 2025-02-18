package dev.wannabe.reciperepository.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.wannabe.reciperepository.model.specialenum.RecipeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;



import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Recipe {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotEmpty
    private String description;

    @Enumerated(EnumType.STRING)
    private RecipeType type;

    @JsonIgnoreProperties("recipe")
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private final Set<RecipeProcess> recipeProcesses = new HashSet<>();


    public Recipe() {
    }

    public Recipe(String name, String description, RecipeType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }


    public Recipe(long id, String name, String description, RecipeType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }


}

package dev.wannabe.reciperepository.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Recipe {


    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private String type;

    @OneToMany(mappedBy = "recipe")
    private final Set<RecipeProcess> recipeProcesses = new HashSet<>();


    public Recipe() {
    }

    public Recipe(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }


    public Recipe(long id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

}

package dev.wannabe.reciperepository.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

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

    public Recipe() {
    }

    public Recipe(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }
}

package dev.wannabe.reciperepository.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Tool {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @Setter
    private String name;

    @Setter
    private String description;

    @ManyToMany(mappedBy = "tools")
    private Set<RecipeProcess> recipeProcesses = new HashSet<>();


    public Tool() {
    }
    public Tool( String name, String description) {
        this.name = name;
        this.description = description;
    }


}

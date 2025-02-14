package dev.wannabe.reciperepository.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Tool {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;


    private String name;
    private String description;


    @JsonIgnoreProperties("tools")
    @ManyToMany(mappedBy = "tools")
    private final Set<RecipeProcess> recipeProcesses = new HashSet<>();


    public Tool() {
    }
    public Tool( String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Tool(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


}

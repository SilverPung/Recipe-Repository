package dev.wannabe.reciperepository.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

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

    public Tool() {
    }
    public Tool( String name, String description) {
        this.name = name;
        this.description = description;
    }


}

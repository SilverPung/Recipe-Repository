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
public class Supplier {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    private String name;
    private String Type;
    private String Email;

    @OneToMany(mappedBy = "supplier")
    private final Set<Ingredient> ingredients = new HashSet<>();

    public Supplier() {
    }

    public Supplier(String name, String Type, String Email) {
        this.name = name;
        this.Type = Type;
        this.Email = Email;
    }


    public Supplier(long id, String name, String Type, String Email) {
        this.id = id;
        this.name = name;
        this.Type = Type;
        this.Email = Email;
    }


}

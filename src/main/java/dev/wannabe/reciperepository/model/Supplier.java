package dev.wannabe.reciperepository.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

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

    public Supplier() {
    }

    public Supplier(String name, String Type, String Email) {
        this.name = name;
        this.Type = Type;
        this.Email = Email;
    }


}

package dev.wannabe.reciperepository.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class RecipeProcess {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "recipeId")
    private Recipe recipe;

    private int stepNumber;
    private String name;
    private String description;
    private long duration;
    private String workStation;
    private String typeofWork;

    @ManyToMany
    @JoinTable(
            name = "ToolForStep",
            joinColumns = @JoinColumn(name = "recipeProcessId"),
            inverseJoinColumns = @JoinColumn(name = "toolId")
    )
    private Set<Tool> tools = new HashSet<>();

    @OneToMany(mappedBy = "recipeProcess")
    private final Set<IngredientForStep> ingredients = new HashSet<>();

    public RecipeProcess() {
    }

    public RecipeProcess(Recipe recipe, int stepNumber, String name, String description, long duration, String workStation, String typeofWork) {
        this.recipe = recipe;
        this.stepNumber = stepNumber;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.workStation = workStation;
        this.typeofWork = typeofWork;
    }


    public RecipeProcess(long id, Recipe recipe, int stepNumber, String name, String description, long duration, String workStation, String typeofWork) {
        this.id = id;
        this.recipe = recipe;
        this.stepNumber = stepNumber;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.workStation = workStation;
        this.typeofWork = typeofWork;
    }

    public void addTool(Tool tool) {
        tools.add(tool);
    }








}

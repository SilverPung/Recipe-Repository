package dev.wannabe.reciperepository.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.wannabe.reciperepository.model.request.RecipeProcessRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"recipeId", "stepNumber"})})
public class RecipeProcess {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnoreProperties("recipeProcesses")
    @ManyToOne
    @JoinColumn(name = "recipeId", nullable = false)
    private Recipe recipe;


    @NotNull
    private int stepNumber;

    @NotBlank
    private String name;

    @NotEmpty
    private String description;

    @NotNull
    private long duration;

    @NotBlank
    private String workStation;

    @NotBlank
    private String typeofWork;


    @ManyToMany
    @JoinTable(
            name = "ToolForStep",
            joinColumns = @JoinColumn(name = "recipeProcessId"),
            inverseJoinColumns = @JoinColumn(name = "toolId")
    )
    private Set<Tool> tools = new HashSet<>();

    @JsonIgnoreProperties("recipeProcess")
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

    public void setData(RecipeProcessRequest recipeProcessRequest) {
        this.name=recipeProcessRequest.getName();
        this.description=recipeProcessRequest.getDescription();
        this.duration=recipeProcessRequest.getDuration();
        this.workStation=recipeProcessRequest.getWorkStation();
        this.typeofWork=recipeProcessRequest.getTypeofWork();
        this.stepNumber=recipeProcessRequest.getStepNumber();
    }

    public void addTool(Tool tool) {
        tools.add(tool);
    }








}

package dev.wannabe.reciperepository.repository;


import dev.wannabe.reciperepository.model.IngredientForStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientForStepRepository extends JpaRepository<IngredientForStep, Long> {
}

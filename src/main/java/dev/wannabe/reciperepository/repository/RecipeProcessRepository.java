package dev.wannabe.reciperepository.repository;


import dev.wannabe.reciperepository.model.RecipeProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeProcessRepository extends JpaRepository<RecipeProcess, Long> {
}

package dev.wannabe.reciperepository.repository;

import dev.wannabe.reciperepository.model.FinalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FinalProductRepository extends JpaRepository<FinalProduct, Long> {


}



package returnevolved.repository;

import org.springframework.data.repository.CrudRepository;
import returnevolved.model.Recipe;

import java.util.List;
import java.util.UUID;

public interface RecipeRepository extends CrudRepository<Recipe, UUID> {

    List<Recipe> findAll();

    List<Recipe> findAllByUserId(UUID userId);

    List<Recipe> findByCategories_Id(UUID category);

    List<Recipe> findByUser_Username(String username);

}

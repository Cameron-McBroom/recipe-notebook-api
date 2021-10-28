package returnevolved.repository;

import org.springframework.data.repository.CrudRepository;
import returnevolved.model.Recipe;

import java.util.List;
import java.util.UUID;

public interface RecipeRepository extends CrudRepository<Recipe, UUID> {

    List<Recipe> findAll();

}

package returnevolved.repository;

import org.springframework.data.repository.CrudRepository;
import returnevolved.model.Ingredient;

import java.util.UUID;

public interface IngredientRepository extends CrudRepository<Ingredient, UUID> {
}

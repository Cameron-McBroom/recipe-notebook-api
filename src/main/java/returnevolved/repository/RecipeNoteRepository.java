package returnevolved.repository;

import org.springframework.data.repository.CrudRepository;
import returnevolved.model.RecipeNote;

import java.util.UUID;

public interface RecipeNoteRepository extends CrudRepository<RecipeNote, UUID> {
}

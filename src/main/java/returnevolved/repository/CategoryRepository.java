package returnevolved.repository;

import org.springframework.data.repository.CrudRepository;
import returnevolved.model.Category;

import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {
}

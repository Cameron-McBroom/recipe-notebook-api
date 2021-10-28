package returnevolved.repository;

import org.springframework.data.repository.CrudRepository;
import returnevolved.model.InstructionStep;

import java.util.UUID;

public interface InstructionRepository extends CrudRepository<InstructionStep, UUID> {
}

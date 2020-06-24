package issoft.isk.geometricconstructor.repository;

import issoft.isk.geometricconstructor.model.entity.FigureType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FigureTypeRepository extends CrudRepository<FigureType, Long> {
    Optional<FigureType> findByName(String name);
}

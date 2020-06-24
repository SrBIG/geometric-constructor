package issoft.isk.geometricconstructor.repository;

import issoft.isk.geometricconstructor.model.entity.FigureTypePropertyValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FigureTypePropetyValueRepository extends CrudRepository<FigureTypePropertyValue, Long> {
    Optional<FigureTypePropertyValue> findByValue(String value);
}

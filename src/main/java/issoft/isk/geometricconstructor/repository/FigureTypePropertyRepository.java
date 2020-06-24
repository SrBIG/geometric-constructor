package issoft.isk.geometricconstructor.repository;

import issoft.isk.geometricconstructor.model.entity.FigureTypeProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FigureTypePropertyRepository extends CrudRepository<FigureTypeProperty, Long> {
    Optional<FigureTypeProperty> findByName(String name);
}

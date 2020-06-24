package issoft.isk.geometricconstructor.repository;

import issoft.isk.geometricconstructor.model.entity.Figure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FigureRepository extends CrudRepository<Figure, Long> {
}

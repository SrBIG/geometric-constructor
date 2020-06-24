package issoft.isk.geometricconstructor.repository;

import issoft.isk.geometricconstructor.model.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
}

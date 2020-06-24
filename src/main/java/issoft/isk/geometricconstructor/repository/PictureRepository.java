package issoft.isk.geometricconstructor.repository;

import issoft.isk.geometricconstructor.model.entity.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {
}

package issoft.isk.geometricconstructor.service;

import issoft.isk.geometricconstructor.model.entity.Picture;
import issoft.isk.geometricconstructor.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class PictureService {

    @Autowired
    final private PictureRepository pictureRepository;

    public Picture save(Picture picture) {
        LocalDateTime now = LocalDateTime.now();
        picture.setLastEditDate(now);

        Long id = picture.getId();

        if (isNull(id) || !pictureRepository.existsById(id)) {
            picture.setDateOfCreation(now);
        }

        return pictureRepository.save(picture);
    }

    public Optional<Picture> findById(Long id) {
        return pictureRepository.findById(id);
    }
}

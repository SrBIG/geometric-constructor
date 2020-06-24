package issoft.isk.geometricconstructor.service;

import issoft.isk.geometricconstructor.model.entity.Picture;
import issoft.isk.geometricconstructor.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PictureService {

    @Autowired
    final private PictureRepository pictureRepository;

    public Picture save(Picture picture) {
        return pictureRepository.save(picture);
    }

    public Optional<Picture> findById(Long id) {
        return pictureRepository.findById(id);
    }
}

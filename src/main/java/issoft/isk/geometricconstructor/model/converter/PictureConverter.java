package issoft.isk.geometricconstructor.model.converter;

import issoft.isk.geometricconstructor.model.dto.GroupDTO;
import issoft.isk.geometricconstructor.model.dto.PictureDTO;
import issoft.isk.geometricconstructor.model.entity.Group;
import issoft.isk.geometricconstructor.model.entity.Picture;
import issoft.isk.geometricconstructor.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class PictureConverter implements EntityConverter<Picture, PictureDTO> {
    @Autowired
    final private PictureService pictureService;
    @Autowired
    final private ModelMapper modelMapper;
    @Autowired
    final private GroupConverter groupConverter;

    @Override
    public PictureDTO toDto(Picture entity) {
        if (isNull(entity)) {
            return null;
        }

        PictureDTO dto = modelMapper.map(entity, PictureDTO.class);
        GroupDTO groupDTO = groupConverter.toDto(entity.getMainGroup());
        dto.setMainGroup(groupDTO);

        return dto;
    }

    @Override
    public Picture toEntity(PictureDTO dto) {
        if (isNull(dto)) {
            return null;
        }

        Picture entity = modelMapper.map(dto, Picture.class);
        Group mainGroup = groupConverter.toEntity(dto.getMainGroup());
        entity.setMainGroup(mainGroup);

        Long id = entity.getId();

        if (nonNull(id)) {
            Optional<Picture> byId = pictureService.findById(id);

            if (byId.isPresent()) {
                Picture picture = byId.get();
                entity.setDateOfCreation(picture.getDateOfCreation());
            } else {
                entity.setId(null);
            }
        }

        return entity;
    }
}

package issoft.isk.geometricconstructor.model.converter;

import issoft.isk.geometricconstructor.model.dto.GroupDTO;
import issoft.isk.geometricconstructor.model.dto.PictureDTO;
import issoft.isk.geometricconstructor.model.entity.Group;
import issoft.isk.geometricconstructor.model.entity.Picture;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class PictureConverter implements EntityConverter<Picture, PictureDTO> {
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

        return entity;
    }
}

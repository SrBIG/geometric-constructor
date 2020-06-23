package issoft.isk.geometricconstructor.model.converter;

import issoft.isk.geometricconstructor.model.dto.FigurePropertyDTO;
import issoft.isk.geometricconstructor.model.entity.FigureProperty;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Converter;

import static java.util.Objects.isNull;

@Converter
@RequiredArgsConstructor
public class FigurePropertyConverter implements EntityConverter<FigureProperty, FigurePropertyDTO> {
    @Autowired
    final private ModelMapper modelMapper;

    @Override
    public FigurePropertyDTO toDto(FigureProperty entity) {
        if (isNull(entity)) {
            return null;
        }

        String name = entity.getProperty().getName();
        String value = entity.getValue().getValue();

        FigurePropertyDTO dto = modelMapper.map(entity, FigurePropertyDTO.class);
        dto.setName(name);
        dto.setValue(value);

        return dto;
    }

    @Override
    public FigureProperty toEntity(FigurePropertyDTO dto) {
        return null;
    }
}

package issoft.isk.geometricconstructor.model.converter;

import issoft.isk.geometricconstructor.model.dto.FigureDTO;
import issoft.isk.geometricconstructor.model.dto.FigurePropertyDTO;
import issoft.isk.geometricconstructor.model.entity.Figure;
import issoft.isk.geometricconstructor.model.entity.FigureProperty;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Converter;

import static java.util.Objects.isNull;

@Converter
@RequiredArgsConstructor
public class FigureConverter implements EntityConverter<Figure, FigureDTO> {
    @Autowired
    final private ModelMapper modelMapper;
    @Autowired
    final private FigurePropertyConverter figurePropertyConverter;

    @Override
    public FigureDTO toDto(Figure entity) {
        if (isNull(entity)) {
            return null;
        }

        String type = entity.getType().getName();
        FigureProperty figureProperty = entity.getProperty();
        FigurePropertyDTO figurePropertyDTO = figurePropertyConverter.toDto(figureProperty);

        FigureDTO dto = modelMapper.map(entity, FigureDTO.class);
        dto.setType(type);
        dto.setProperty(figurePropertyDTO);

        return dto;
    }

    @Override
    public Figure toEntity(FigureDTO dto) {
        return null;
    }
}

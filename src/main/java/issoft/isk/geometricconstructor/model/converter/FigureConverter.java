package issoft.isk.geometricconstructor.model.converter;

import issoft.isk.geometricconstructor.model.converter.exception.ConvertException;
import issoft.isk.geometricconstructor.model.dto.FigureDTO;
import issoft.isk.geometricconstructor.model.dto.FigurePropertyDTO;
import issoft.isk.geometricconstructor.model.entity.Figure;
import issoft.isk.geometricconstructor.model.entity.FigureProperty;
import issoft.isk.geometricconstructor.model.entity.FigureType;
import issoft.isk.geometricconstructor.service.FigureService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class FigureConverter implements EntityConverter<Figure, FigureDTO> {
    @Autowired
    final private FigureService figureService;
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
        if (isNull(dto)) {
            return null;
        }

        Figure entity = modelMapper.map(dto, Figure.class);

        FigureType figureType = convertToFigureType(dto.getType());
        entity.setType(figureType);

        FigureProperty figureProperty = convertToFigureProperty(dto.getProperty(), figureType);
        entity.setProperty(figureProperty);

        Long id = entity.getId();

        if (nonNull(id)) {
            Optional<Figure> byId = figureService.findById(id);

            if (byId.isEmpty()) {
                entity.setId(null);
            }
        }

        return entity;
    }

    public FigureType convertToFigureType(String typeName) {
        Optional<FigureType> figureTypeOptional = figureService.findFigureTypeByName(typeName);

        if (figureTypeOptional.isPresent()) {
            return figureTypeOptional.get();
        } else {
            String msg = "Figure type " + typeName + " does not exist";

            throw new ConvertException(msg);
        }
    }

    public FigureProperty convertToFigureProperty(FigurePropertyDTO figurePropertyDTO, FigureType figureType) {
        FigureProperty figureProperty = figurePropertyConverter.toEntity(figurePropertyDTO);

        if (figureService.canFigureTypeHaveProperty(figureType, figureProperty)) {
            return figureProperty;
        } else {
            String msg = "Figure type " + figureType.getName() +
                    " can not have property " + figureProperty.getProperty().getName() +
                    " with value - " + figureProperty.getValue().getValue();

            throw new ConvertException(msg);
        }
    }
}

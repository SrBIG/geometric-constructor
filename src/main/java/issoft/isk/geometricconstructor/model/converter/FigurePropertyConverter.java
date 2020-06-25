package issoft.isk.geometricconstructor.model.converter;

import issoft.isk.geometricconstructor.model.converter.exception.ConvertException;
import issoft.isk.geometricconstructor.model.dto.FigurePropertyDTO;
import issoft.isk.geometricconstructor.model.entity.FigureProperty;
import issoft.isk.geometricconstructor.model.entity.FigureTypeProperty;
import issoft.isk.geometricconstructor.model.entity.FigureTypePropertyValue;
import issoft.isk.geometricconstructor.service.FigureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class FigurePropertyConverter implements EntityConverter<FigureProperty, FigurePropertyDTO> {
    @Autowired
    final private FigureService figureService;

    @Override
    public FigurePropertyDTO toDto(FigureProperty entity) {
        if (isNull(entity)) {
            return null;
        }

        String name = entity.getProperty().getName();
        String value = entity.getValue().getValue();

        return new FigurePropertyDTO(name, value);
    }

    @Override
    public FigureProperty toEntity(FigurePropertyDTO dto) {
        if (isNull(dto)) {
            return null;
        }

        String name = dto.getName();
        String value = dto.getValue();

        FigureProperty entity;

        Optional<FigureTypeProperty> propertyOptional = figureService.findFigureTypePropertyByName(name);
        FigureTypePropertyValue propertyValue = figureService.getFigureTypePropertyValueByValue(value);

        if (propertyOptional.isPresent()) {
            FigureTypeProperty property = propertyOptional.get();

            if (figureService.canFigureTypePropertyHaveValue(property.getName(), propertyValue.getValue())) {
                entity = new FigureProperty(property, propertyValue);
            } else {
                String msg = "Property " + name + " can not have value - " + value;

                throw new ConvertException(msg);
            }
        } else {
            String msg = "Property " + name + " does not exist";

            throw new ConvertException(msg);
        }

        return entity;
    }
}

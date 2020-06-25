package issoft.isk.geometricconstructor.service;

import issoft.isk.geometricconstructor.model.entity.Figure;
import issoft.isk.geometricconstructor.model.entity.FigureType;
import issoft.isk.geometricconstructor.model.entity.FigureTypeProperty;
import issoft.isk.geometricconstructor.model.entity.FigureTypePropertyValue;
import issoft.isk.geometricconstructor.repository.FigureRepository;
import issoft.isk.geometricconstructor.repository.FigureTypePropertyRepository;
import issoft.isk.geometricconstructor.repository.FigureTypePropetyValueRepository;
import issoft.isk.geometricconstructor.repository.FigureTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class FigureService {
    @Autowired
    final private FigureRepository figureRepository;
    @Autowired
    final private FigureTypeRepository figureTypeRepository;
    @Autowired
    final private FigureTypePropertyRepository figureTypePropertyRepository;
    @Autowired
    final private FigureTypePropetyValueRepository figureTypePropetyValueRepository;

    public Figure save(Figure picture) {
        return figureRepository.save(picture);
    }

    public Optional<Figure> findById(Long id) {
        return figureRepository.findById(id);
    }

    public Optional<FigureType> findFigureTypeByName(String figureTypeName) {
        return figureTypeRepository.findByName(figureTypeName);
    }

    public Optional<FigureTypeProperty> findFigureTypePropertyByName(String figureTypePropertyName) {
        return figureTypePropertyRepository.findByName(figureTypePropertyName);
    }

    public FigureTypePropertyValue getFigureTypePropertyValueByValue(String figureTypePropertyValue) {
        Optional<FigureTypePropertyValue> byValue = figureTypePropetyValueRepository.findByValue(figureTypePropertyValue);

        if (byValue.isPresent()) {
            return byValue.get();
        } else {
            FigureTypePropertyValue value = new FigureTypePropertyValue();
            value.setValue(figureTypePropertyValue);

            return figureTypePropetyValueRepository.save(value);
        }
    }

    @Transactional
    public boolean canFigureTypeHaveProperty(String figureTypeName, String propertyName) {
        boolean isAllowedProperty = false;

        Optional<FigureType> figureTypeByName = findFigureTypeByName(figureTypeName);
        Optional<FigureTypeProperty> figureTypePropertyByName = findFigureTypePropertyByName(propertyName);

        if (figureTypeByName.isPresent() && figureTypePropertyByName.isPresent()){
            FigureType figureType = figureTypeByName.get();
            FigureTypeProperty figureTypeProperty = figureTypePropertyByName.get();

            List<FigureTypeProperty> allowedProperties = figureType.getProperties();

            isAllowedProperty = allowedProperties.isEmpty() || allowedProperties.contains(figureTypeProperty);
        }

        return isAllowedProperty;
    }

    @Transactional
    public boolean canFigureTypePropertyHaveValue(String propertyName, String value) {
        boolean isAllowedValue = false;

        Optional<FigureTypeProperty> byId = findFigureTypePropertyByName(propertyName);

        if (byId.isPresent()) {
            FigureTypeProperty property = byId.get();
            List<FigureTypePropertyValue> defaultValues = property.getDefaultValues();
            isAllowedValue = nonNull(value);

            if (!CollectionUtils.isEmpty(defaultValues)) {
                isAllowedValue = defaultValues.stream()
                        .map(FigureTypePropertyValue::getValue)
                        .collect(Collectors.toList())
                        .contains(value);
            }
        }

        return isAllowedValue;
    }
}

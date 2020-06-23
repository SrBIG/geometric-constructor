package issoft.isk.geometricconstructor.model.converter;

import issoft.isk.geometricconstructor.model.dto.FigureDTO;
import issoft.isk.geometricconstructor.model.dto.GroupDTO;
import issoft.isk.geometricconstructor.model.entity.FigureItem;
import issoft.isk.geometricconstructor.model.entity.Group;
import issoft.isk.geometricconstructor.model.entity.GroupItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Converter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Converter
@RequiredArgsConstructor
public class GroupConverter implements EntityConverter<Group, GroupDTO> {
    @Autowired
    final private ModelMapper modelMapper;
    @Autowired
    final private FigureConverter figureConverter;

    @Override
    public GroupDTO toDto(Group entity) {
        if (isNull(entity)) {
            return null;
        }

        Map<Integer, GroupDTO> groups = extractGroups(entity.getGroups());
        Map<Integer, FigureDTO> figures = extractFigures(entity.getFigures());

        GroupDTO dto = modelMapper.map(entity, GroupDTO.class);
        dto.setGroups(groups);
        dto.setFigures(figures);

        return dto;
    }

    @Override
    public Group toEntity(GroupDTO dto) {
        return null;
    }

    private Map<Integer, GroupDTO> extractGroups(List<GroupItem> groupItems) {
        Map<Integer, GroupDTO> groups = new HashMap<>();

        for (GroupItem groupItem : groupItems) {
            Integer number = groupItem.getNumber();
            GroupDTO figureDTO = toDto(groupItem.getGroup());
            groups.put(number, figureDTO);
        }

        return groups;
    }

    private Map<Integer, FigureDTO> extractFigures(List<FigureItem> figureItems) {
        Map<Integer, FigureDTO> figures = new HashMap<>();

        for (FigureItem figureItem : figureItems) {
            Integer number = figureItem.getNumber();
            FigureDTO figureDTO = figureConverter.toDto(figureItem.getFigure());
            figures.put(number, figureDTO);
        }

        return figures;
    }
}

package issoft.isk.geometricconstructor.model.converter;

import issoft.isk.geometricconstructor.model.converter.exception.ConvertException;
import issoft.isk.geometricconstructor.model.dto.FigureDTO;
import issoft.isk.geometricconstructor.model.dto.GroupDTO;
import issoft.isk.geometricconstructor.model.entity.Figure;
import issoft.isk.geometricconstructor.model.entity.FigureItem;
import issoft.isk.geometricconstructor.model.entity.Group;
import issoft.isk.geometricconstructor.model.entity.GroupItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
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
        if (isNull(dto)) {
            return null;
        }

        Group entity = modelMapper.map(dto, Group.class);

        List<FigureItem> figureItems = convertToFigureItems(dto.getFigures());
        List<GroupItem> groupItems = convertToGroupItems(dto.getGroups());

        checkNumbers(figureItems, groupItems);

        entity.setFigures(figureItems);
        entity.setGroups(groupItems);

        return entity;
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

    private List<GroupItem> convertToGroupItems(Map<Integer, GroupDTO> groups) {
        List<GroupItem> groupItems = new ArrayList<>();

        if (nonNull(groups)){
            for (Map.Entry<Integer, GroupDTO> entry : groups.entrySet()) {
                Integer number = entry.getKey();
                Group group = toEntity(entry.getValue());
                GroupItem figureItem = new GroupItem(number, group);
                groupItems.add(figureItem);
            }
        }

        return groupItems;
    }

    private List<FigureItem> convertToFigureItems(Map<Integer, FigureDTO> figures) {
        List<FigureItem> figureItems = new ArrayList<>();

        for (Map.Entry<Integer, FigureDTO> entry : figures.entrySet()) {
            Integer number = entry.getKey();
            Figure figure = figureConverter.toEntity(entry.getValue());
            FigureItem figureItem = new FigureItem(number, figure);
            figureItems.add(figureItem);
        }

        return figureItems;
    }

    private void checkNumbers(List<FigureItem> figureItems, List<GroupItem> groupItems) {
        List<Integer> numbers = figureItems.stream()
                .map(FigureItem::getNumber)
                .collect(Collectors.toList());

        groupItems.stream()
                .map(GroupItem::getNumber)
                .forEach(numbers::add);

        Set<Integer> set = new HashSet<>(numbers);

        if (set.size() < numbers.size()) {
            String msg = "Group contains wrong element numbers";

            throw new ConvertException(msg);
        }
    }
}

package issoft.isk.geometricconstructor.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupDTO extends PictureElementDTO {
    String displayMethod;
    Map<Integer, GroupDTO> groups;
    Map<Integer, FigureDTO> figures;
}

package issoft.isk.geometricconstructor.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupDTO {

    Long id;

    @NotBlank
    String displayMethod;

    Map<Integer, GroupDTO> groups;

    Map<Integer, FigureDTO> figures;
}

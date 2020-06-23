package issoft.isk.geometricconstructor.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FigurePropertyDTO {
    String name;
    String value;
}

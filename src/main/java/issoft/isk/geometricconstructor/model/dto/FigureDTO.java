package issoft.isk.geometricconstructor.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FigureDTO {

    Long id;

    @NotBlank
    String type;

    FigurePropertyDTO property;
}

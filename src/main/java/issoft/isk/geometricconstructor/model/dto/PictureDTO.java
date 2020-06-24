package issoft.isk.geometricconstructor.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PictureDTO {

    Long id;

    @NotBlank
    String name;

    LocalDateTime dateOfCreation;

    LocalDateTime lastEditDate;

    GroupDTO mainGroup;
}

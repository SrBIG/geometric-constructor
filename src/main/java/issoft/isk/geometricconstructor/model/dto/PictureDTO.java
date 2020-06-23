package issoft.isk.geometricconstructor.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PictureDTO {
    Long id;
    String name;
    LocalDateTime dateOfCreation;
    LocalDateTime lastEditDate;
    GroupDTO mainGroup;
}

package issoft.isk.geometricconstructor.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Embeddable
@Table(name = "figure_property_xref")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FigureProperty {

    @ManyToOne
    @JoinColumn(name = "figure_type_property_id", nullable = false)
    FigureTypeProperty property;

    @ManyToOne
    @JoinColumn(name = "figure_type_property_value_id", nullable = false)
    FigureTypePropertyValue value;
}

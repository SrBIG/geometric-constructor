package issoft.isk.geometricconstructor.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "figure_type_property_value")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FigureTypePropertyValue {

    @Id
    @GeneratedValue
    @Column(name = "figure_type_propety_value_id", nullable = false)
    Long id;

    @Column(name = "value")
    String value;
}

package issoft.isk.geometricconstructor.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "figure_type_property")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FigureTypeProperty {

    @Id
    @GeneratedValue
    @Column(name = "figure_type_property_id", nullable = false)
    Long id;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "figure_type_property_value_xref", referencedColumnName = "figure_type_property_id")
    List<FigureTypePropertyValue> values;
}

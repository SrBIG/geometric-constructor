package issoft.isk.geometricconstructor.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Embeddable
@Table(name = "figure_item_xref")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FigureItem {

    @Column(name = "number")
    Integer number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "figure_item_id", nullable = false)
    Figure figure;
}
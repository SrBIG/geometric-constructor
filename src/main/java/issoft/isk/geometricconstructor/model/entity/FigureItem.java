package issoft.isk.geometricconstructor.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Embeddable
@Table(name = "figure_item_xref")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FigureItem {

    @Column(name = "number")
    Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "figure_item_id", nullable = false)
    Figure figure;
}
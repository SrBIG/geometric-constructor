package issoft.isk.geometricconstructor.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "figure_group")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "group_id", nullable = false)
    Long id;

    @Column(name = "display_method")
    @Enumerated(EnumType.STRING)
    DisplayMethod displayMethod;

    @ElementCollection
    @CollectionTable(
            name = "group_item_xref",
            joinColumns = @JoinColumn(name = "group_id", nullable=false, insertable=false))
    List<GroupItem> groups;

    @ElementCollection
    @CollectionTable(
            name = "figure_item_xref",
            joinColumns = @JoinColumn(name = "group_id", nullable=false, insertable=false))
    List<FigureItem> figures;
}

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
@Table(name = "group_item_xref")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupItem {

    @Column(name = "number")
    Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_item_id", nullable = false)
    Group group;
}
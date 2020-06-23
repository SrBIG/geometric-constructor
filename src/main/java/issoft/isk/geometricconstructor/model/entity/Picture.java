package issoft.isk.geometricconstructor.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "picture")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Picture {

    @Id
    @GeneratedValue
    @Column(name = "picture_id", nullable = false)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "date_of_creation", nullable = false)
    LocalDateTime dateOfCreation;

    @Column(name = "last_edit_date", nullable = false)
    LocalDateTime lastEditDate;

    @OneToOne
    @JoinColumn(name = "group_id", unique = true)
    Group mainGroup;
}

package by.eis.task.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "\"person\"")
@EqualsAndHashCode(callSuper = true, exclude = "propertyList")
@ToString(callSuper = true, exclude = "propertyList")
@NoArgsConstructor
@AllArgsConstructor
public class Person extends BaseEntity {
    protected String email;

    @OneToMany(
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "owner"
    )
    private List<Property> propertyList = new ArrayList<>();
}

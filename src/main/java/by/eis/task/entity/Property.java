package by.eis.task.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "\"property\"")
@EqualsAndHashCode(callSuper = true, exclude = "owner")
@ToString(callSuper = true)
@NoArgsConstructor
public class Property extends BaseEntity {
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person owner;
}

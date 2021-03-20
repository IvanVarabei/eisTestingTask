package by.eis.task.converter;

import by.eis.task.dto.PropertyDto;
import by.eis.task.entity.Property;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PropertyConverter {
    PropertyDto toDTO(Property property);
}

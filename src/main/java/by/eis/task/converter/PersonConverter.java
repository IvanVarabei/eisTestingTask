package by.eis.task.converter;

import by.eis.task.dto.PersonDto;
import by.eis.task.entity.Person;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonConverter {
    PersonDto toDTO(Person person);

    Person toPerson(PersonDto personDto);
}

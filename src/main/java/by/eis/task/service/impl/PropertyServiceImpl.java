package by.eis.task.service.impl;

import by.eis.task.converter.PropertyConverter;
import by.eis.task.dto.PropertyDto;
import by.eis.task.entity.Person;
import by.eis.task.exception.ErrorMessage;
import by.eis.task.exception.ResourceNotFoundException;
import by.eis.task.repository.PersonRepository;
import by.eis.task.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PersonRepository personRepository;
    private final PropertyConverter propertyConverter;

    @Override
    public List<PropertyDto> getPropertiesByPersonId(Long personId) {
        Person person = personRepository.findById(personId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND, personId)));
        return person.getPropertyList().stream().map(propertyConverter::toDTO).collect(Collectors.toList());
    }
}

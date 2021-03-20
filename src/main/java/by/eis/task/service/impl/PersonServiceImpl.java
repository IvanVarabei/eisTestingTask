package by.eis.task.service.impl;

import by.eis.task.converter.PersonConverter;
import by.eis.task.dto.PersonDto;
import by.eis.task.entity.Person;
import by.eis.task.exception.ErrorMessage;
import by.eis.task.exception.ForbiddenActionException;
import by.eis.task.exception.ResourceNotFoundException;
import by.eis.task.repository.PersonRepository;
import by.eis.task.repository.PropertyRepository;
import by.eis.task.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final PropertyRepository propertyRepository;

    @Override
    @Transactional
    public PersonDto save(PersonDto personDto) {
        Person person = personConverter.toPerson(personDto);
        return personConverter.toDTO(personRepository.save(person));
    }

    @Override
    public PersonDto getById(Long personId) {
        Person person = personRepository.findById(personId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND, personId)));
        return personConverter.toDTO(person);
    }

    @Override
    public Page<PersonDto> getAllPaginated(Pageable pageable) {
        Page<Person> page = personRepository.findAll(pageable);
        if (pageable.getPageNumber() > page.getTotalPages() - 1) {
            throw new ResourceNotFoundException(String.format(
                    ErrorMessage.PAGE_NOT_FOUND,
                    pageable.getPageSize(),
                    pageable.getPageNumber(),
                    page.getTotalPages() - 1));
        }
        return page.map(personConverter::toDTO);
    }

    @Override
    @Transactional
    public PersonDto updatePerson(PersonDto personDto) {
        Long personId = personDto.getId();
        Person existedPerson = personRepository.findById(personId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND, personId)));
        Person update = personConverter.toPerson(personDto);
        boolean isOtherUserPropertiesPresentAmongUpdate = update.getPropertyList().stream()
                .filter(p -> propertyRepository.findById(p.getId()).isPresent())
                .map(p -> propertyRepository.findById(p.getId()).get())
                .anyMatch(p -> !p.getOwner().equals(existedPerson));
        if (isOtherUserPropertiesPresentAmongUpdate) {
            throw new ForbiddenActionException(ErrorMessage.OTHER_USERS_PROPERTY);
        }
        boolean isExistingPropertiesHaveBeenChanged = update.getPropertyList().stream()
                .filter(p -> propertyRepository.findById(p.getId()).isPresent())
                .anyMatch(p -> !p.equals(propertyRepository.findById(p.getId()).get()));
        if (isExistingPropertiesHaveBeenChanged) {
            throw new ForbiddenActionException(ErrorMessage.PROPERTY_STATE_CAN_T_BE_CHANGED);
        }
        existedPerson.setEmail(update.getEmail());
        existedPerson.getPropertyList().clear();
        existedPerson.getPropertyList().addAll(update.getPropertyList());
        existedPerson.getPropertyList().forEach(p -> p.setOwner(existedPerson));
        return personConverter.toDTO(personRepository.save(existedPerson));
    }
}

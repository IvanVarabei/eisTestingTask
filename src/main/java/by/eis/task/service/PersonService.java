package by.eis.task.service;

import by.eis.task.dto.PersonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {
    PersonDto save(PersonDto personDto);

    PersonDto getById(Long id);

    Page<PersonDto> getAllPaginated(Pageable pageable);

    /**
     * We can create and delete properties using this method.
     */
    PersonDto updatePerson(PersonDto personDto);
}

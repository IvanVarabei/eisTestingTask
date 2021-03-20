package by.eis.task.controller;

import by.eis.task.dto.PersonDto;
import by.eis.task.dto.PropertyDto;
import by.eis.task.service.PersonService;
import by.eis.task.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
@Validated
public class PersonController {
    private final PersonService personService;
    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody @Valid PersonDto personDto) {
        return ResponseEntity.ok(personService.save(personDto));
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> getById(@PathVariable("personId") @Min(1) Long personId) {
        return ResponseEntity.ok(personService.getById(personId));
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable, PagedResourcesAssembler<PersonDto> pagedAssembler) {
        Page<PersonDto> page = personService.getAllPaginated(pageable);
        return ResponseEntity.ok(pagedAssembler.toModel(page));
    }

    @GetMapping("/{personId}/properties")
    public ResponseEntity<List<PropertyDto>> getOrdersByUserId(@PathVariable("personId") @Min(1) Long personId) {
        List<PropertyDto> properties = propertyService.getPropertiesByPersonId(personId);
        return ResponseEntity.ok().body(properties);
    }

    @PutMapping
    public ResponseEntity<PersonDto> updatePerson(@Valid @RequestBody PersonDto personDto) {
        return ResponseEntity.ok().body(personService.updatePerson(personDto));
    }
}

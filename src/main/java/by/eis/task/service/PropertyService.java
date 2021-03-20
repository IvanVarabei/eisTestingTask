package by.eis.task.service;

import by.eis.task.dto.PropertyDto;

import java.util.List;

public interface PropertyService {
    List<PropertyDto> getPropertiesByPersonId(Long personId);
}

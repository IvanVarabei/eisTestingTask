package by.eis.task.converter;

import by.eis.task.dto.PolicyDto;
import by.eis.task.entity.Policy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PolicyConverter {
    PolicyDto toDTO(Policy policy);

    Policy toPolicy(PolicyDto policyDto);
}

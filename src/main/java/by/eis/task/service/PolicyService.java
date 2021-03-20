package by.eis.task.service;

import by.eis.task.dto.PolicyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PolicyService {
    PolicyDto save(PolicyDto policy);

    PolicyDto getById(Long policyId);

    Page<PolicyDto> getAllPaginated(Pageable pageable);

    void delete(Long policyId);
}

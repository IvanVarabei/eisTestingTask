package by.eis.task.service.impl;

import by.eis.task.converter.PolicyConverter;
import by.eis.task.dto.PolicyDto;
import by.eis.task.entity.Policy;
import by.eis.task.entity.Property;
import by.eis.task.exception.ErrorMessage;
import by.eis.task.exception.ResourceNotFoundException;
import by.eis.task.repository.PolicyRepository;
import by.eis.task.repository.PropertyRepository;
import by.eis.task.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {
    private final PolicyRepository policyRepository;
    private final PropertyRepository propertyRepository;
    private final PolicyConverter policyConverter;

    @Override
    @Transactional
    public PolicyDto save(PolicyDto policyDto) {
        Long propertyId = policyDto.getProperty().getId();
        Property property = propertyRepository.findById(propertyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND, propertyId)));
        Policy policy = policyConverter.toPolicy(policyDto);
        policy.setProperty(property);
        policy.setCreatedDate(LocalDateTime.now());
        policy = policyRepository.save(policy);
        return policyConverter.toDTO(policy);
    }

    @Override
    public PolicyDto getById(Long policyId) {
        Policy policy = policyRepository.findById(policyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND, policyId)));
        return policyConverter.toDTO(policy);
    }

    @Override
    public Page<PolicyDto> getAllPaginated(Pageable pageable) {
        Page<Policy> page = policyRepository.findAll(pageable);
        if (pageable.getPageNumber() > page.getTotalPages() - 1) {
            throw new ResourceNotFoundException(String.format(
                    ErrorMessage.PAGE_NOT_FOUND,
                    pageable.getPageSize(),
                    pageable.getPageNumber(),
                    page.getTotalPages() - 1));
        }
        return page.map(policyConverter::toDTO);
    }

    @Override
    @Transactional
    public void delete(Long policyId) {
        Policy policy = policyRepository.findById(policyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND, policyId)));
        policyRepository.delete(policy);
    }
}

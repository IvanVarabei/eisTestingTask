package by.eis.task.controller;

import by.eis.task.dto.PolicyDto;
import by.eis.task.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/policies")
@RequiredArgsConstructor
@Validated
public class PolicyController {
    private final PolicyService policyService;

    @PostMapping
    public ResponseEntity<PolicyDto> create(@RequestBody @Valid PolicyDto policyDto) {
        return ResponseEntity.ok(policyService.save(policyDto));
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<PolicyDto> getById(@PathVariable("policyId") @Min(1) Long policyId) {
        return ResponseEntity.ok(policyService.getById(policyId));
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable, PagedResourcesAssembler<PolicyDto> pagedAssembler) {
        Page<PolicyDto> page = policyService.getAllPaginated(pageable);
        return ResponseEntity.ok(pagedAssembler.toModel(page));
    }

    @DeleteMapping("/{policyId}")
    public ResponseEntity<PolicyDto> deleteById(@PathVariable("policyId") @Min(1) Long policyId) {
        policyService.delete(policyId);
        return ResponseEntity.noContent().build();
    }
}

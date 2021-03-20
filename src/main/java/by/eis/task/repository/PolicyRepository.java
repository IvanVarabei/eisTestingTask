package by.eis.task.repository;

import by.eis.task.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, String> {
    Optional<Policy> findById(Long id);
}

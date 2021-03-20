package by.eis.task.repository;

import by.eis.task.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, String> {
    Optional<Property> findById(Long id);
}

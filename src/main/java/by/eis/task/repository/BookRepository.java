package by.eis.task.repository;


import by.eis.task.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,String> {

    BookEntity findById(Long id);

}
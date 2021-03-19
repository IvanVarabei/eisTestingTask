package by.eis.task.service;

import by.eis.task.entity.BookEntity;
import by.eis.task.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String getBookStats(Long id) {
        BookEntity book = bookRepository.findById(id);
        String result = "{ID : " + book.getId().toString() + ",Title : " + book.getTitle() + ",Author :" + book.getAuthor() + " }";

        return result;
    }
}
package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> listBooks() {
        return bookRepository.findAll();
    }

    public BookEntity save(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    public List<BookEntity> saveList(List<BookEntity> listOfBookEntities) {
        return bookRepository.saveAll(listOfBookEntities);
    }
    public Optional<BookEntity> getById(long id) {
        return listBooks().stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }


}

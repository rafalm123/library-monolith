package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> listBooks() {
        return bookRepository.findAll();
    }

    public Optional<BookEntity> getBookById(long id) {
        return listBooks().stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    public Optional<BookEntity> getBookByUuid(UUID uuid) {
        return listBooks().stream()
                .filter(book -> book.getBookUuid().equals(uuid))
                .findFirst();
    }

    public BookEntity saveBook(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    public List<BookEntity> saveList(List<BookEntity> listOfBookEntities) {
        return bookRepository.saveAll(listOfBookEntities);
    }

    public void removeBook(BookEntity bookEntity){
        bookRepository.delete(bookEntity);
    }
}

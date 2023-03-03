package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.BookCopyEntity;
import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;

    @Autowired
    public BookCopyService(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    public List<BookCopyEntity> listCopies(){
        return bookCopyRepository.findAll();
    }

    public Optional<BookCopyEntity> getCopyById(long id) {
        return listCopies().stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    public Optional<BookCopyEntity> getCopyByUuid(UUID uuid) {
        return listCopies().stream()
                .filter(book -> book.getBookCopyUuid().equals(uuid))
                .findFirst();
    }

    public BookCopyEntity saveCopy(BookCopyEntity bookCopyEntity){
        return bookCopyRepository.save(bookCopyEntity);
    }

    public void removeCopy(BookCopyEntity bookCopyEntity){
        bookCopyRepository.delete(bookCopyEntity);
    }
}

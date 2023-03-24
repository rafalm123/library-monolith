package com.library.monolith.common.repository;

import com.library.monolith.common.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookEntityById(Long id);

    Book getBookById(Long id);
}

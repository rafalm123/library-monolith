package com.library.monolith.common.repository.book;

import com.library.monolith.common.model.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByAuthorAndTitle (String author,String title);
}

package com.library.monolith.common.repository.book;

import com.library.monolith.common.model.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

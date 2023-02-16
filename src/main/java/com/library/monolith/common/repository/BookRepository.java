package com.library.monolith.common.repository;

import com.library.monolith.common.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<java.awt.print.Book> findBookByAuthor(String author);

}

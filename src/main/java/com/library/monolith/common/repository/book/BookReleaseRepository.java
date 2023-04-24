package com.library.monolith.common.repository.book;

import com.library.monolith.common.model.entity.book.Book;
import com.library.monolith.common.model.entity.book.BookRelease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookReleaseRepository extends JpaRepository<BookRelease,Long> {

    Optional<BookRelease> findByBook(Book book);
}

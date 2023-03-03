package com.library.monolith.common.repository;

import com.library.monolith.common.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findBookByAuthor(String author);

    Optional<BookEntity> findBookByBookUuid(UUID uuid);

}

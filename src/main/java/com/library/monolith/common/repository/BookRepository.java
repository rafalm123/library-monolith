package com.library.monolith.common.repository;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.model.entity.BookReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findBookByBookUuid(UUID uuid);

    Optional<BookEntity> findBookEntityById(Long id);
}

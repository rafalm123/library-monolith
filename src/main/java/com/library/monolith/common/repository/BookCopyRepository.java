package com.library.monolith.common.repository;

import com.library.monolith.common.model.entity.BookCopyEntity;
import com.library.monolith.common.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookCopyRepository extends JpaRepository<BookCopyEntity,Long> {

    Optional<BookCopyEntity> findBookCopyByBookCopyUuid(UUID uuid);


}

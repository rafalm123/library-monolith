package com.library.monolith.common.repository;

import com.library.monolith.common.model.entity.BookReleaseEntity;
import com.library.monolith.common.model.entity.ReleaseCopyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookReleaseRepository extends JpaRepository<BookReleaseEntity,Long> {

    Optional<BookReleaseEntity> findBookReleaseByBookReleaseUuid(UUID uuid);

}

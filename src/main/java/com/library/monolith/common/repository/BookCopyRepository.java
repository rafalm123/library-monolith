package com.library.monolith.common.repository;

import com.library.monolith.common.model.entity.ReleaseCopyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookCopyRepository extends JpaRepository<ReleaseCopyEntity,Long> {

    Optional<ReleaseCopyEntity> findBookCopyByBookCopyUuid(UUID uuid);


}

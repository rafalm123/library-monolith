package com.library.monolith.common.repository.book;

import com.library.monolith.common.model.entity.book.ReleaseCopyVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseCopyVersionRepository extends JpaRepository<ReleaseCopyVersion,Long> {
}

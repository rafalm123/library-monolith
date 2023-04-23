package com.library.monolith.common.repository.book;

import com.library.monolith.common.model.entity.book.ReleaseCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseCopyRepository extends JpaRepository<ReleaseCopy,Long> {
}

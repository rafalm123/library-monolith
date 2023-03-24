package com.library.monolith.common.repository;

import com.library.monolith.common.model.entity.BookRelease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookReleaseRepository extends JpaRepository<BookRelease,Long> {
}

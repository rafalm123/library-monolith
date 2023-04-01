package com.library.monolith.common.repository.book;

import com.library.monolith.common.model.entity.book.BookRelease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReleaseRepository extends JpaRepository<BookRelease,Long> {
}

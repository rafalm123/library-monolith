package com.library.monolith.common.repository.user;

import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryUserVersionRepository extends JpaRepository<LibraryUserVersion,Long> {
}

package com.library.monolith.common.repository.user;

import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryUserVersionRepository extends JpaRepository<LibraryUserVersion,Long> {

    Optional<LibraryUserVersion> findByLibraryUser(LibraryUser libraryUser);

}

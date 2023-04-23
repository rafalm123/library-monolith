package com.library.monolith.common.repository.user;

import com.library.monolith.common.model.entity.user.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {

    Optional<LibraryUser> findByUsername(String username);

    Optional<LibraryUser> findByLibraryCode(Long libraryCode);

    Optional<LibraryUser> findByUsernameAndLibraryCode(String username, Long libraryCode);
}

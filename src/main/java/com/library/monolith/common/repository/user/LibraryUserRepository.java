package com.library.monolith.common.repository.user;

import com.library.monolith.common.model.entity.book.BookRelease;
import com.library.monolith.common.model.entity.user.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {

    LibraryUser findAllByUsername(String username);


}

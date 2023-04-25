package com.library.monolith.common.repository.user;

import com.library.monolith.common.model.entity.user.LibraryUser;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AllArgsConstructor
public class LibraryUserRepositoryTest {

    private final LibraryUserRepository libraryUserRepository;

    @Test
    void findByUsername() {
        LibraryUser user = new LibraryUser();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setLibraryCode(123456L);
        user.setCreateDate(Timestamp.from(Instant.now()));

        libraryUserRepository.save(user);

        Optional<LibraryUser> optionalUser = libraryUserRepository.findByUsername("testuser");

        Assertions.assertTrue(optionalUser.isPresent());
        Assertions.assertEquals(user, optionalUser.get());
    }

    @Test
    void findByLibraryCode() {
        LibraryUser user = new LibraryUser();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setLibraryCode(123456L);
        user.setCreateDate(Timestamp.from(Instant.now()));

        libraryUserRepository.save(user);

        Optional<LibraryUser> optionalUser = libraryUserRepository.findByLibraryCode(123456L);

        Assertions.assertTrue(optionalUser.isPresent());
        Assertions.assertEquals(user, optionalUser.get());
    }

    @Test
    void findByUsernameAndLibraryCode() {
        LibraryUser user = new LibraryUser();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setLibraryCode(123456L);
        user.setCreateDate(Timestamp.from(Instant.now()));

        libraryUserRepository.save(user);

        Optional<LibraryUser> optionalUser = libraryUserRepository.findByUsernameAndLibraryCode("testuser", 123456L);

        Assertions.assertTrue(optionalUser.isPresent());
        Assertions.assertEquals(user, optionalUser.get());
    }

}

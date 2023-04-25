package com.library.monolith.common.repository.user;

import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LibraryUserVersionRepositoryTest {

    private final LibraryUserVersionRepository libraryUserVersionRepository;
    private final TestEntityManager entityManager;

    @Autowired
    public LibraryUserVersionRepositoryTest(LibraryUserVersionRepository libraryUserVersionRepository, TestEntityManager entityManager) {
        this.libraryUserVersionRepository = libraryUserVersionRepository;
        this.entityManager = entityManager;
    }

    @Test
    public void shouldFindByLibraryUser() {
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setUsername("user");
        libraryUser.setPassword("password");
        libraryUser.setLibraryCode(123456L);
        libraryUser.setCreateDate(Timestamp.from(Instant.now()));
        entityManager.persist(libraryUser);

        LibraryUserVersion libraryUserVersion = new LibraryUserVersion();
        libraryUserVersion.setLibraryUser(libraryUser);
        libraryUserVersion.setNickname("nick");
        libraryUserVersion.setName("name");
        libraryUserVersion.setSurname("surname");
        libraryUserVersion.setEmail("email@email.com");
        libraryUserVersion.setDebt(null);
        libraryUserVersion.setStartValidity(Timestamp.from(Instant.now()));
        entityManager.persist(libraryUserVersion);

        Optional<LibraryUserVersion> found = libraryUserVersionRepository.findByLibraryUser(libraryUser);

        assertThat(found).isPresent();
        assertThat(found.get().getNickname()).isEqualTo(libraryUserVersion.getNickname());
        assertThat(found.get().getName()).isEqualTo(libraryUserVersion.getName());
        assertThat(found.get().getSurname()).isEqualTo(libraryUserVersion.getSurname());
        assertThat(found.get().getEmail()).isEqualTo(libraryUserVersion.getEmail());
        assertThat(found.get().getDebt()).isEqualTo(libraryUserVersion.getDebt());
        assertThat(found.get().getStartValidity()).isEqualTo(libraryUserVersion.getStartValidity());
        assertThat(found.get().getEndValidity()).isEqualTo(libraryUserVersion.getEndValidity());
    }

    @Test
    public void shouldNotFindByLibraryUser() {
            LibraryUser libraryUser = new LibraryUser();
            libraryUser.setUsername("user");
            libraryUser.setPassword("password");
            libraryUser.setLibraryCode(123456L);
            libraryUser.setCreateDate(Timestamp.from(Instant.now()));
            entityManager.persist(libraryUser);

            Optional<LibraryUserVersion> found = libraryUserVersionRepository.findByLibraryUser(libraryUser);

            assertThat(found).isEmpty();
        }

    }

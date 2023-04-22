package com.library.monolith.common.repository.user;

import com.library.monolith.common.model.entity.user.LibraryUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class LibraryUserRepositoryTest {

    @Autowired
    private LibraryUserRepository libraryUserRepo;
    @Autowired
    private RoleRepository roleRepo;

    @Test
    void itShouldCreateUser() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        LibraryUser.Builder builder = new LibraryUser.Builder();
        builder.setUsername("user111");
        builder.setPassword(passwordEncoder.encode("password111"));
        builder.setCreateDate(Timestamp.from(Instant.now()));
        builder.setLibraryCode(3213213L);
        builder.setLibraryUserVersions(null);
        builder.addRole(roleRepo.findByName("REGULAR"));

        LibraryUser libraryUser = builder.build();
        LibraryUser save = libraryUserRepo.save(libraryUser);

        assertThat(save).isNotNull();
        assertThat(save.getId()).isGreaterThan(0);
    }
}
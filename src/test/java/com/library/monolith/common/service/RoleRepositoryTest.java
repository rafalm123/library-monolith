package com.library.monolith.common.service;

import com.library.monolith.common.repository.user.LibraryUserRepository;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase
public class RoleRepositoryTest {

    @Mock
    private LibraryUserRepository libraryUserRepository;



}

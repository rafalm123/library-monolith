package com.library.monolith.common.repository.user;

import com.library.monolith.common.model.entity.user.Address;
import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@DataJpaTest
public class AddressRepositoryTest {

    private final AddressRepository addressRepository;
    private final TestEntityManager entityManager;

    @Autowired
    public AddressRepositoryTest(AddressRepository addressRepository, TestEntityManager entityManager) {
        this.addressRepository = addressRepository;
        this.entityManager = entityManager;
    }

    @Test
    public void itShouldFindByLibraryUserVersion() {
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
        entityManager.persist(libraryUserVersion);

        Address address = new Address();
        address.setLibraryUserVersion(libraryUserVersion);
        address.setStreet("Street 1");
        address.setCity("City 1");
        address.setState("State 1");
        address.setPostalCode("123456");
        address.setCountry("Country 1");
        entityManager.persist(address);

        Optional<Address> found = addressRepository.findByLibraryUserVersion(libraryUserVersion);

        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(found.get().getStreet(), address.getStreet());
        Assertions.assertEquals(found.get().getCity(), address.getCity());
        Assertions.assertEquals(found.get().getState(), address.getState());
        Assertions.assertEquals(found.get().getPostalCode(), address.getPostalCode());
        Assertions.assertEquals(found.get().getCountry(), address.getCountry());
    }

    @Test
    public void itShouldNotFindByLibraryUserVersion() {
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
        entityManager.persist(libraryUserVersion);

        Address address = new Address();
        address.setLibraryUserVersion(libraryUserVersion);
        address.setStreet("Street 1");
        address.setCity("City 1");
        address.setState("State 1");
        address.setPostalCode("123456");
        address.setCountry("Country 1");
        entityManager.persist(address);

        LibraryUserVersion anotherLibraryUserVersion = new LibraryUserVersion();
        anotherLibraryUserVersion.setLibraryUser(libraryUser);
        anotherLibraryUserVersion.setNickname("anotherNick");
        anotherLibraryUserVersion.setName("anotherName");
        anotherLibraryUserVersion.setSurname("anotherSurname");
        anotherLibraryUserVersion.setEmail("another@email.com");
        entityManager.persist(anotherLibraryUserVersion);

        Optional<Address> found = addressRepository.findByLibraryUserVersion(anotherLibraryUserVersion);

        Assertions.assertTrue(found.isEmpty());
    }

}

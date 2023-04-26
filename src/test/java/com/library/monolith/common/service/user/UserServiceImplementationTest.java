package com.library.monolith.common.service.user;

import com.library.monolith.common.exception.user.UserError;
import com.library.monolith.common.exception.user.UserException;
import com.library.monolith.common.model.dto.user.*;
import com.library.monolith.common.model.entity.user.*;
import com.library.monolith.common.repository.user.AddressRepository;
import com.library.monolith.common.repository.user.LibraryUserRepository;
import com.library.monolith.common.repository.user.LibraryUserVersionRepository;
import com.library.monolith.common.repository.user.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplementationTest {

    @InjectMocks
    private UserServiceImplementation userService;

    @Mock
    private LibraryUserVersionRepository libraryUserVersionRepository;
    @Mock
    private LibraryUserRepository libraryUserRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private LibraryUser user;
    private LibraryUserVersion userVersion;
    private Address address;
    private Role role;

    @BeforeEach
    public void setUp() {
        role = Role.builder().name("REGULAR").build();
        user = LibraryUser.builder()
                .username("testuser")
                .password("testuser")
                .libraryCode(111111111L)
                .roles(Set.of(role))
                .build();
        userVersion = LibraryUserVersion.builder()
                .name("Piotr")
                .surname("Nowak")
                .nickname("piter")
                .email("pnowak@gmail.com")
                .libraryUser(user)
                .build();
        address = Address.builder()
                .street("Warszawska")
                .city("Rzeszow")
                .state("Podkarpackie") // stejt XD
                .postalCode("35555")
                .country("Poland")
                .libraryUserVersion(userVersion)
                .build();
        userVersion.setAddress(address);
        user.setLibraryUserVersions(Collections.singletonList(userVersion));
    }

    @Test
    public void isShouldGetLibraryUserDetailsDto() {
        when(libraryUserVersionRepository.findById(1L)).thenReturn(Optional.of(userVersion));

        LibraryUserDetailsDTO result = userService.getLibraryUserDetailsDto(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Piotr");
        assertThat(result.getSurname()).isEqualTo("Nowak");
        assertThat(result.getEmail()).isEqualTo("pnowak@gmail.com");
        assertThat(result.getLibraryCode()).isEqualTo(111111111L);
        assertThat(result.getStreet()).isEqualTo("Warszawska");
        assertThat(result.getCity()).isEqualTo("Rzeszow");
        assertThat(result.getState()).isEqualTo("Podkarpackie");
        assertThat(result.getPostalCode()).isEqualTo("35555");
        assertThat(result.getCountry()).isEqualTo("Poland");
    }

    @Test
    public void isShouldThrowsUserNotFoundException() {
        when(libraryUserVersionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getLibraryUserDetailsDto(1L))
                .isInstanceOf(UserException.class)
                .satisfies(exception -> {
                    assertThat(((UserException) exception).getUserError())
                            .isEqualTo(UserError.LIBRARY_USER_VERSION_NOT_FOUND);
                });
    }

    @Test
    public void itShouldGetLibraryUserOverviewDto() {
        when(libraryUserVersionRepository.findById(1L)).thenReturn(Optional.of(userVersion));

        LibraryUserOverviewDTO result = userService.getLibraryUserOverviewDto(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Piotr");
        assertThat(result.getSurname()).isEqualTo("Nowak");
        assertThat(result.getEmail()).isEqualTo("pnowak@gmail.com");
        assertThat(result.getLibraryCode()).isEqualTo(111111111L);
    }

    @Test
    public void itShouldGetLibraryUserOverviewDtoList() {
        when(libraryUserVersionRepository.findAll()).thenReturn(Collections.singletonList(userVersion));

        List<LibraryUserOverviewDTO> resultList = userService.getLibraryUserOverviewDtoList();

        assertThat(resultList).isNotNull();
        assertThat(resultList).hasSize(1);
        assertThat(resultList.get(0).getName()).isEqualTo("Piotr");
        assertThat(resultList.get(0).getSurname()).isEqualTo("Nowak");
        assertThat(resultList.get(0).getEmail()).isEqualTo("pnowak@gmail.com");
        assertThat(resultList.get(0).getLibraryCode()).isEqualTo(111111111L);
    }

    @Test
    public void itShouldLoadUserByUsername() {
        when(libraryUserRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        UserDetails result = userService.loadUserByUsername("testuser");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("testuser");
        assertThat(result.getAuthorities()).isNotEmpty();
    }

    @Test
    public void itShouldThrowsUsernameNotFoundException() {
        when(libraryUserRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.loadUserByUsername("testuser"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("User not found");
    }

    @Test
    public void itShouldRegisterUser() {
        LibraryUserRegistrationDTO registrationDTO = new LibraryUserRegistrationDTO("testuser2",
                "password",
                "Janusz",
                "Nowak",
                "jnowak",
                "jnowak@gmail.com",
                "Hetmanska",
                "Rzeszow",
                "Podkarpacie",
                "11111",
                "Poland");

        when(roleRepository.findByName("REGULAR")).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(any())).thenReturn("encoded_password");
        when(libraryUserRepository.save(any())).thenReturn(user);
        when(libraryUserVersionRepository.save(any())).thenReturn(userVersion);
        when(addressRepository.save(any())).thenReturn(address);

        LibraryUserOverviewDTO result = userService.registerUser(registrationDTO);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Janusz");
        assertThat(result.getSurname()).isEqualTo("Nowak");
        assertThat(result.getEmail()).isEqualTo("jnowak@gmail.com");

        verify(libraryUserRepository, times(1)).save(any());
        verify(libraryUserVersionRepository, times(1)).save(any());
        verify(addressRepository, times(1)).save(any());
    }

    @Test
    public void itShouldDeleteUser() {
        LibraryUser user = new LibraryUser();
        user.setUsername("testuser");
        user.setLibraryCode(111111111L);

        when(libraryUserRepository.findByUsernameAndLibraryCode("testuser", 111111111L)).thenReturn(Optional.of(user));

        String result = userService.deleteUser(new LibraryUserDeleteDTO("testuser", 111111111L));

        assertEquals(user.getUsername() + " with library code " + user.getLibraryCode() + " was deleted", result);

        verify(libraryUserRepository, times(1)).findByUsernameAndLibraryCode("testuser", 111111111L);
        verify(libraryUserRepository, times(1)).delete(user);
    }

    @Test
    public void itShouldEditUser() {
        LibraryUser user = new LibraryUser();
        user.setUsername("testuser");
        user.setLibraryCode(111111111L);

        LibraryUserVersion libraryUserVersion = new LibraryUserVersion("Piotr", "Nowak", "pnowak", "pnowak@gmail.com");
        libraryUserVersion.setLibraryUser(user);

        List<LibraryUserVersion> libraryUserVersions = new ArrayList<>();
        libraryUserVersions.add(libraryUserVersion);
        user.setLibraryUserVersions(libraryUserVersions);

        when(libraryUserRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        LibraryUserUpdateDTO libraryUserUpdateDTO = new LibraryUserUpdateDTO();
        libraryUserUpdateDTO.setName("updated name");
        libraryUserUpdateDTO.setSurname("updated surname");
        libraryUserUpdateDTO.setNickname("updated nickname");
        libraryUserUpdateDTO.setEmail("updated email@email.com");
        libraryUserUpdateDTO.setStreet("updated street");
        libraryUserUpdateDTO.setCity("updated city");
        libraryUserUpdateDTO.setState("updated state");
        libraryUserUpdateDTO.setPostalCode("updated postal");
        libraryUserUpdateDTO.setCountry("updated country");

        String result = userService.editUser("testuser", libraryUserUpdateDTO);

        assertEquals("User testuser was edited", result);

        verify(libraryUserRepository, times(1)).findByUsername("testuser");
        verify(libraryUserVersionRepository, times(1)).save(any(LibraryUserVersion.class));
    }
}
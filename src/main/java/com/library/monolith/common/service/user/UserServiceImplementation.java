package com.library.monolith.common.service.user;

import com.library.monolith.common.exception.user.UserError;
import com.library.monolith.common.exception.user.UserException;
import com.library.monolith.common.mapping.user.LibraryUserDetailsDtoMapper;
import com.library.monolith.common.mapping.user.LibraryUserOverviewDtoMapper;
import com.library.monolith.common.mapping.user.LibraryUserRegistrationDtoMapper;
import com.library.monolith.common.model.dto.user.LibraryUserDeleteDTO;
import com.library.monolith.common.model.dto.user.LibraryUserDetailsDTO;
import com.library.monolith.common.model.dto.user.LibraryUserOverviewDTO;
import com.library.monolith.common.model.dto.user.LibraryUserRegistrationDTO;
import com.library.monolith.common.model.entity.user.Address;
import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import com.library.monolith.common.model.entity.user.Role;
import com.library.monolith.common.repository.user.AddressRepository;
import com.library.monolith.common.repository.user.LibraryUserRepository;
import com.library.monolith.common.repository.user.LibraryUserVersionRepository;
import com.library.monolith.common.repository.user.RoleRepository;
import com.library.monolith.common.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserDetailsService, UserService {

    private final LibraryUserVersionRepository libraryUserVersionRepository;
    private final LibraryUserRepository libraryUserRepository;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    public LibraryUserDetailsDTO getLibraryUserDetailsDto(Long id) {
        LibraryUserVersion libraryUserVersion = libraryUserVersionRepository.findById(id).orElseThrow(() -> {
            throw new UserException(UserError.LIBRARY_USER_VERSION_NOT_FOUND);
        });
        return LibraryUserDetailsDtoMapper.getInstance().toLibraryUserDetailsDto(
                libraryUserVersion.getLibraryUser(), libraryUserVersion, libraryUserVersion.getAddress());
    }

    public LibraryUserOverviewDTO getLibraryUserOverviewDto(Long id) {
        {
            LibraryUserVersion libraryUserVersion = libraryUserVersionRepository.findById(id).orElseThrow(() -> {
                throw new UserException(UserError.LIBRARY_USER_VERSION_NOT_FOUND);
            });
            return LibraryUserOverviewDtoMapper.getInstance().toLibraryUserOverviewDto(
                    libraryUserVersion.getLibraryUser(),
                    libraryUserVersion);
        }
    }

    public List<LibraryUserOverviewDTO> getLibraryUserOverviewDtoList() {

        List<LibraryUserVersion> libraryUserVersions = libraryUserVersionRepository.findAll();
        return libraryUserVersions.stream().map(libraryUserVersion ->
                        LibraryUserOverviewDtoMapper.getInstance()
                                .toLibraryUserOverviewDto(libraryUserVersion.getLibraryUser(),
                                        libraryUserVersion))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return libraryUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public LibraryUserOverviewDTO addUser(LibraryUserRegistrationDTO registrationDTO){
        LibraryUserRegistrationDtoMapper getInstance = LibraryUserRegistrationDtoMapper.getInstance();
        LibraryUser libraryUser = getInstance.toLibraryUser(registrationDTO);

        roleRepository.findByName("REGULAR").ifPresent(role -> {
            libraryUser.setRoles(Collections.singleton(role));
        });
        libraryUser.setPassword(passwordEncoder.encode(libraryUser.getPassword()));
        libraryUserRepository.save(libraryUser);

        LibraryUserVersion libraryUserVersion = getInstance.toLibraryUserVersion(registrationDTO);
        libraryUserVersionRepository.save(libraryUserVersion);

        Address address = getInstance.toAddress(registrationDTO);
        addressRepository.save(address);

        return LibraryUserOverviewDtoMapper.getInstance().toLibraryUserOverviewDto(libraryUser,libraryUserVersion);
    }

    public void deleteUser(LibraryUserDeleteDTO deleteDTO){
        LibraryUser libraryUser = libraryUserRepository.findByUsernameAndLibraryCode(deleteDTO.getUsername()
                , deleteDTO.getLibraryCode()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        libraryUserRepository.delete(libraryUser);
    }

    public void editUser(LibraryUserRegistrationDTO registrationDTO){

        LibraryUser libraryUser = libraryUserRepository.findByUsername(registrationDTO.getUsername()
        ).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        li
    }
}


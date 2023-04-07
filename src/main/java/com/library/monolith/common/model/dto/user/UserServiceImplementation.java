package com.library.monolith.common.model.dto.user;

import com.library.monolith.common.exception.user.UserError;
import com.library.monolith.common.exception.user.UserException;
import com.library.monolith.common.mapping.user.LibraryUserDetailsDtoMapper;
import com.library.monolith.common.mapping.user.LibraryUserOverviewDtoMapper;
import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import com.library.monolith.common.repository.user.LibraryUserRepository;
import com.library.monolith.common.repository.user.LibraryUserVersionRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserDetailsService,UserService {

    private final LibraryUserVersionRepository libraryUserVersionRepository;
    private LibraryUserRepository libraryUserRepository;
    private final PasswordEncoder passwordEncoder;

    public LibraryUserDetailsDTO getLibraryUserDetailsDto(Long id) {
        LibraryUserVersion libraryUserVersion = libraryUserVersionRepository.findById(id).orElseThrow(() -> {
            throw new UserException(UserError.USER_VERSION_NOT_FOUND);
        });
        return LibraryUserDetailsDtoMapper.getInstance().toLibraryUserDetailsDto(
                libraryUserVersion.getLibraryUser(), libraryUserVersion, libraryUserVersion.getAddress());
    }

    public LibraryUserOverviewDTO getLibraryUserOverviewDto(Long id) {
        {
            LibraryUserVersion libraryUserVersion = libraryUserVersionRepository.findById(id).orElseThrow(() -> {
                throw new UserException(UserError.USER_VERSION_NOT_FOUND);
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
                                .toLibraryUserOverviewDto(libraryUserVersion.getLibraryUser(), libraryUserVersion))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return libraryUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}


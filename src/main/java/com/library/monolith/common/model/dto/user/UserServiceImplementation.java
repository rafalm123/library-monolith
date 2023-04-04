package com.library.monolith.common.model.dto.user;

import com.library.monolith.common.exception.user.UserError;
import com.library.monolith.common.exception.user.UserException;
import com.library.monolith.common.mapping.user.LibraryUserDetailsDtoMapper;
import com.library.monolith.common.mapping.user.LibraryUserOverviewDtoMapper;
import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import com.library.monolith.common.repository.user.LibraryUserRepository;
import com.library.monolith.common.repository.user.LibraryUserVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final LibraryUserVersionRepository libraryUserVersionRepository;

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
                            .toLibraryUserOverviewDto(libraryUserVersion.getLibraryUser(),libraryUserVersion))
                    .collect(Collectors.toList());
    }


    }


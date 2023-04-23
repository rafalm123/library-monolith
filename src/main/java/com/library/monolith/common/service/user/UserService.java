package com.library.monolith.common.service.user;


import com.library.monolith.common.model.dto.user.LibraryUserDeleteDTO;
import com.library.monolith.common.model.dto.user.LibraryUserDetailsDTO;
import com.library.monolith.common.model.dto.user.LibraryUserOverviewDTO;
import com.library.monolith.common.model.dto.user.LibraryUserRegistrationDTO;
import com.library.monolith.common.model.entity.user.LibraryUser;

import java.util.List;

public interface UserService {

    LibraryUserDetailsDTO getLibraryUserDetailsDto(Long id);

    LibraryUserOverviewDTO getLibraryUserOverviewDto(Long id);

    List<LibraryUserOverviewDTO> getLibraryUserOverviewDtoList();

//    Page<LibraryUserOverviewDTO> getLibraryUserOverviewPage();

    LibraryUserOverviewDTO addUser(LibraryUserRegistrationDTO registrationDTO);

    void deleteUser (LibraryUserDeleteDTO deleteDTO);

    void editUser (LibraryUserRegistrationDTO registrationDTO);
}

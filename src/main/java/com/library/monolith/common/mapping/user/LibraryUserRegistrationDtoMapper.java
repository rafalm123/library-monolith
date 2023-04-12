package com.library.monolith.common.mapping.user;

import com.library.monolith.common.model.dto.user.LibraryUserRegistrationDTO;
import com.library.monolith.common.model.entity.user.Address;
import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface LibraryUserRegistrationDtoMapper {

    static LibraryUserRegistrationDtoMapper getInstance() {
        return Mappers.getMapper(LibraryUserRegistrationDtoMapper.class);
    }

    @Mapping(source = "libraryUser.username", target = "username")
    @Mapping(source = "libraryUser.password", target = "password")
    @Mapping(source = "libraryUserVersion.name", target = "name")
    @Mapping(source = "libraryUserVersion.surname", target = "surname")
    @Mapping(source = "libraryUserVersion.nickname", target = "nickname")
    @Mapping(source = "libraryUserVersion.email", target = "email")
    @Mapping(source = "address.street", target = "street")
    @Mapping(source = "address.city", target = "city")
    @Mapping(source = "address.state", target = "state")
    @Mapping(source = "address.postalCode", target = "postalCode")
    @Mapping(source = "address.country", target = "country")
    LibraryUserRegistrationDTO toLibraryUserRegistrationDto(LibraryUser libraryUser,
                                                            LibraryUserVersion libraryUserVersion,
                                                            Address address);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", expression = "java(java.sql.Timestamp.from(java.time.Instant.now()))")
    @Mapping(target = "libraryCode", expression = "java(new java.util.Random().nextLong(900000000) + 100000000)")
    @Mapping(target = "libraryUserVersions", ignore = true)
    LibraryUser toLibraryUser(LibraryUserRegistrationDTO libraryUserRegistrationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "start_validity", expression = "java(java.sql.Timestamp.from(java.time.Instant.now()))")
    @Mapping(target = "end_validity", expression = "java(java.sql.Timestamp.from(java.time.Instant.now().plus(java.time.Duration.ofDays(180))))")
    @Mapping(target = "debt", ignore = true)
    @Mapping(target = "libraryUser", ignore = true)
    @Mapping(target = "address", ignore = true)
    LibraryUserVersion toLibraryUserVersion(LibraryUserRegistrationDTO libraryUserRegistrationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "libraryUserVersion", ignore = true)
    Address toAddress(LibraryUserRegistrationDTO libraryUserRegistrationDTO);
}

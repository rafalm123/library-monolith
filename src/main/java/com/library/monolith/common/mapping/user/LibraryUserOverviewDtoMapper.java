package com.library.monolith.common.mapping.user;

import com.library.monolith.common.model.dto.user.LibraryUserOverviewDTO;
import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import com.library.monolith.common.model.entity.user.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface LibraryUserOverviewDtoMapper {

    static LibraryUserOverviewDtoMapper getInstance() {
        return Mappers.getMapper(LibraryUserOverviewDtoMapper.class);
    }

    default String mapRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.joining(", "));
    }

    @Mapping(source = "libraryUserVersion.name", target = "name")
    @Mapping(source = "libraryUserVersion.surname", target = "surname")
    @Mapping(source = "libraryUser.libraryCode", target = "libraryCode")
    @Mapping(source = "libraryUserVersion.email", target = "email")
    @Mapping(target = "role", expression = "java(mapRoles(libraryUser.getRoles()))")
    LibraryUserOverviewDTO toLibraryUserOverviewDto(LibraryUser libraryUser,
                                                    LibraryUserVersion libraryUserVersion
    );

}
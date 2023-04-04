package com.library.monolith.common.mapping.user;

import com.library.monolith.common.model.dto.user.LibraryUserDetailsDTO;
import com.library.monolith.common.model.dto.user.LibraryUserOverviewDTO;
import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LibraryUserOverviewDtoMapper {

    static LibraryUserOverviewDtoMapper getInstance(){
        return Mappers.getMapper(LibraryUserOverviewDtoMapper.class);
    }

    @Mapping(target = "address.street", ignore = true)
    @Mapping(target = "address.state", ignore = true)
    @Mapping(target = "address.postalCode", ignore = true)
    @Mapping(target = "address.country", ignore = true)
    @Mapping(target = "address.city", ignore = true)
    @Mapping(source = "libraryUser.name", target = "name")
    @Mapping(source = "libraryUser.surname", target = "surname")
    @Mapping(source = "libraryUser.libraryCode", target = "libraryCode")
    @Mapping(source = "libraryUserVersion.email", target = "email")
    LibraryUserOverviewDTO toLibraryUserOverviewDto(LibraryUser libraryUser,
                                                        LibraryUserVersion libraryUserVersion);

}
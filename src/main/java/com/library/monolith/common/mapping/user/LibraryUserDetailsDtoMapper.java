package com.library.monolith.common.mapping.user;

import com.library.monolith.common.model.dto.user.LibraryUserDetailsDTO;
import com.library.monolith.common.model.entity.user.Address;
import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LibraryUserDetailsDtoMapper {
    static LibraryUserDetailsDtoMapper getInstance() {
        return  Mappers.getMapper(LibraryUserDetailsDtoMapper.class);
    }

    @Mapping(source = "libraryUser.name", target = "name")
    @Mapping(source = "libraryUser.surname", target = "surname")
    @Mapping(source = "libraryUser.libraryCode", target = "libraryCode")
    @Mapping(source = "libraryUserVersion.nickname", target = "nickname")
    @Mapping(source = "libraryUser.createDate", target = "createDate")
    @Mapping(source = "libraryUserVersion.email", target = "email")
    @Mapping(source = "address.street", target = "street")
    @Mapping(source = "address.city", target = "city")
    @Mapping(source = "address.state", target = "state")
    @Mapping(source = "address.postalCode", target = "postalCode")
    @Mapping(source = "address.country", target = "country")
    LibraryUserDetailsDTO toLibraryUserDetailsDto(LibraryUser libraryUser,
                                                  LibraryUserVersion libraryUserVersion,
                                                  Address address);

}
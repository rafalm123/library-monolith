package com.library.monolith.common.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapping;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryUserOverviewDTO {

    private String name;
    private String surname;
    private Long libraryCode;
    private String email;

}

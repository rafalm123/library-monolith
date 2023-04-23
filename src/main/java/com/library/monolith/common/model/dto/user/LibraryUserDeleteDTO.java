package com.library.monolith.common.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LibraryUserDeleteDTO {

    private String username;
    private Long libraryCode;

}

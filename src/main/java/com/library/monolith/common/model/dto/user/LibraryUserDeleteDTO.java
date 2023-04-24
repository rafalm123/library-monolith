package com.library.monolith.common.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryUserDeleteDTO {

    private String username;
    private Long libraryCode;

}

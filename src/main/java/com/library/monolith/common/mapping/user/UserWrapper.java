package com.library.monolith.common.mapping.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.monolith.common.model.entity.user.LibraryUser;
import lombok.Data;

import java.util.List;

@Data
public class UserWrapper {

    @JsonProperty("libraryUser")
    private List<LibraryUser> users;

}

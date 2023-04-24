package com.library.monolith.common.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryUserUpdateDTO {

    private String name;
    private String surname;
    private String nickname;
    private String email;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

}

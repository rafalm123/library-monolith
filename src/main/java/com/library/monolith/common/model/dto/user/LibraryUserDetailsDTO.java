package com.library.monolith.common.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryUserDetailsDTO {

    private String name;
    private String surname;
    private String nickname;
    private Timestamp createDate;
    private String email;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Long libraryCode;
}

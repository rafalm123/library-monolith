package com.library.monolith.common.model.entity.user;

import com.library.monolith.common.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "library_user")
public class LibraryUser extends BaseEntity{

    @Column(name = "create_date")
    private Timestamp createDate;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_surname")
    private String surname;
    @Column(name = "library_code")
    private Long libraryCode;

    @OneToMany(mappedBy = "libraryUser",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LibraryUserVersion> libraryUserVersions;

}

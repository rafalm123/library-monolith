package com.library.monolith.common.model.entity.user;

import com.library.monolith.common.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
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

//    @Column(name = "username")
//    private String username;
//    @Column(name = "user_password")
//    private String password;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "libraryUser",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LibraryUserVersion> libraryUserVersions;

    public LibraryUser(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }
}

package com.library.monolith.common.model.entity.user;

import com.library.monolith.common.config.UserRole;
import com.library.monolith.common.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "library_user")
public class LibraryUser extends BaseEntity implements UserDetails{

    @Column(name = "username",unique = true)
    private String username;
    @Column(name = "password",unique = true)
    private String password;
    @Column(name = "create_date")
    private Timestamp createDate;
    @Column(name = "library_code",unique = true)
    private Long libraryCode;
    @Column(name = "user_role")
    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

    @OneToMany(mappedBy = "libraryUser",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LibraryUserVersion> libraryUserVersions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

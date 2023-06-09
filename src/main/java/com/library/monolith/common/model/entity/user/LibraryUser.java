package com.library.monolith.common.model.entity.user;

import com.library.monolith.common.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "library_user")
public class LibraryUser extends BaseEntity implements UserDetails{

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "create_date")
    private Timestamp createDate;
    @Column(name = "library_code",unique = true)
    private Long libraryCode;
    @OneToMany(mappedBy = "libraryUser",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LibraryUserVersion> libraryUserVersions;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
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

    public LibraryUserVersion getLatestVersion() {
        return this.getLibraryUserVersions().stream()
                .max(Comparator.comparing(LibraryUserVersion::getStartValidity))
                .orElseThrow(() -> new RuntimeException("No version found"));
    }
}

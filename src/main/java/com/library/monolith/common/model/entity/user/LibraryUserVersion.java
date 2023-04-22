package com.library.monolith.common.model.entity.user;

import com.library.monolith.common.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "library_user_version")
public class LibraryUserVersion extends BaseEntity {

    @Column(name = "user_name")
    private String name;
    @Column(name = "user_surname")
    private String surname;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "debt")
    private BigDecimal debt;
    @Column(name = "start_validity")
    private Timestamp start_validity;
    @Column(name = "end_validity")
    private Timestamp end_validity;
    @ManyToOne
    @JoinColumn(name = "library_user_id",referencedColumnName = "id")
    private LibraryUser libraryUser;
    @OneToOne(mappedBy = "libraryUserVersion",cascade = CascadeType.ALL,orphanRemoval = true)
    private Address address;

    public LibraryUserVersion(String name,
                              String surname,
                              String nickname,
                              String email
                              ) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.email = email;
        this.debt = null;
        this.start_validity = Timestamp.from(Instant.now());
        this.end_validity = null;
    }
}

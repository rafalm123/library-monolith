package com.library.monolith.common.model.entity.user;

import com.library.monolith.common.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "library_user_version")
public class LibraryUserVersion extends BaseEntity {

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
    @OneToOne(mappedBy = "libraryUserVersion",cascade = CascadeType.ALL)
    private Address address;

}

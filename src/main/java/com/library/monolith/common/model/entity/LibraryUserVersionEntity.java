package com.library.monolith.common.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "library_user_version", schema = "library", catalog = "library")
public class LibraryUserVersionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;
    @Basic
    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "address", nullable = false, length = 50)
    private String address;
    @Basic
    @Column(name = "postal_code", nullable = false, length = 50)
    private String postalCode;
    @Basic
    @Column(name = "debt")
    private BigDecimal debt;
    @Basic
    @Column(name = "start_validity", nullable = false)
    private Timestamp startValidity;
    @Basic
    @Column(name = "end_validity")
    private Timestamp endValidity;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private LibraryUserEntity libraryUserByUserIdEntity;
}

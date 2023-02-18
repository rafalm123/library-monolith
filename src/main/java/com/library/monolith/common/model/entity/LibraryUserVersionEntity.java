package com.library.monolith.common.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LibraryUserVersionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long userId;
    private String nickname;
    private String email;
    private String address;
    private String postalCode;
    private BigDecimal debt;
    private Timestamp startValidity;
    private Timestamp endValidity;
    @ManyToOne
    @JoinColumn(name = "library_user_entity_id", referencedColumnName = "id")
    private LibraryUserEntity libraryUserByUserIdEntity;
}

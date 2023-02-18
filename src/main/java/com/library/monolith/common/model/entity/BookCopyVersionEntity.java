package com.library.monolith.common.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookCopyVersionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long bookCopyId;
    private Timestamp startValidity;
    private Timestamp endValidity;
    private String status;
    private String notes;
    @ManyToOne
    @JoinColumn(name = "book_copy_entity_id", referencedColumnName = "id")
    private BookCopyEntity bookCopyByBookCopyIdEntity;
}

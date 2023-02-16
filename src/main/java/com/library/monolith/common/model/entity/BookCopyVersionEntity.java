package com.library.monolith.common.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "book_copy_version", schema = "library", catalog = "library")
public class BookCopyVersionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "book_copy_id", insertable = false, updatable = false)
    private Long bookCopyId;
    @Basic
    @Column(name = "start_validity", nullable = false)
    private Timestamp startValidity;
    @Basic
    @Column(name = "end_validity", nullable = false)
    private Timestamp endValidity;
    @Basic
    @Column(name = "status", nullable = false, length = 15)
    private String status;
    @Basic
    @Column(name = "notes", length = 200)
    private String notes;
    @ManyToOne
    @JoinColumn(name = "book_copy_id", referencedColumnName = "id")
    private BookCopyEntity bookCopyByBookCopyIdEntity;
}

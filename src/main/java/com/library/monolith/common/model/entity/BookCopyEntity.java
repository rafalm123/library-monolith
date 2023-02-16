package com.library.monolith.common.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "book_copy", schema = "library", catalog = "library")
public class BookCopyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "book_id", insertable = false, updatable = false)
    private Long bookId;
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "book_copy_uuid", nullable = false)
    private UUID bookCopyUuid;
    @Basic
    @Column(name = "copy_language", nullable = false, length = 50)
    private String copyLanguage;
    @Basic
    @Column(name = "cover_type", nullable = false, length = 50)
    private String coverType;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity bookByBookIdEntity;
}

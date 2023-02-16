package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "book_copy", schema = "library", catalog = "library")
public class BookCopy {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "book_id", nullable = true, insertable = false, updatable = false)
    private Long bookId;
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "book_copy_uuid", nullable = false)
    private UUID bookCopyUuid;
    @Basic
    @Column(name = "copy_language", nullable = false, length = -1)
    private String copyLanguage;
    @Basic
    @Column(name = "cover_type", nullable = false, length = -1)
    private String coverType;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book bookByBookId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getCopyLanguage() {
        return copyLanguage;
    }

    public void setCopyLanguage(String copyLanguage) {
        this.copyLanguage = copyLanguage;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public UUID getBookCopyUuid() {
        return bookCopyUuid;
    }

    public void setBookCopyUuid(UUID bookCopyUuid) {
        this.bookCopyUuid = bookCopyUuid;
    }

    public BookCopy() {
    }

    public BookCopy(String copyLanguage, String coverType, Book bookByBookId) {
        this.copyLanguage = copyLanguage;
        this.coverType = coverType;
        this.bookByBookId = bookByBookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCopy that = (BookCopy) o;
        return Objects.equals(id, that.id) && Objects.equals(bookId, that.bookId) && Objects.equals(bookCopyUuid, that.bookCopyUuid) && Objects.equals(copyLanguage, that.copyLanguage) && Objects.equals(coverType, that.coverType) && Objects.equals(bookByBookId, that.bookByBookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, bookCopyUuid, copyLanguage, coverType, bookByBookId);
    }

    public Book getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(Book bookByBookId) {
        this.bookByBookId = bookByBookId;
    }
}

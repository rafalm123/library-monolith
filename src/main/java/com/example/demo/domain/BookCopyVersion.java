package com.example.demo.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "book_copy_version", schema = "library", catalog = "library")
public class BookCopyVersion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "book_copy_id", nullable = true, insertable = false, updatable = false)
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
    @Column(name = "notes", nullable = true, length = 200)
    private String notes;
    @ManyToOne
    @JoinColumn(name = "book_copy_id", referencedColumnName = "id")
    private BookCopy bookCopyByBookCopyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(Long bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public Timestamp getStartValidity() {
        return startValidity;
    }

    public void setStartValidity(Timestamp startValidity) {
        this.startValidity = startValidity;
    }

    public Timestamp getEndValidity() {
        return endValidity;
    }

    public void setEndValidity(Timestamp endValidity) {
        this.endValidity = endValidity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BookCopyVersion() {
    }

    public BookCopyVersion(Timestamp startValidity, Timestamp endValidity, String status, String notes, BookCopy bookCopyByBookCopyId) {
        this.startValidity = startValidity;
        this.endValidity = endValidity;
        this.status = status;
        this.notes = notes;
        this.bookCopyByBookCopyId = bookCopyByBookCopyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCopyVersion that = (BookCopyVersion) o;
        return Objects.equals(id, that.id) && Objects.equals(bookCopyId, that.bookCopyId) && Objects.equals(startValidity, that.startValidity)
                && Objects.equals(endValidity, that.endValidity) && Objects.equals(status, that.status) && Objects.equals(notes, that.notes) && Objects.equals(bookCopyByBookCopyId, that.bookCopyByBookCopyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookCopyId, startValidity, endValidity, status, notes, bookCopyByBookCopyId);
    }

    public BookCopy getBookCopyByBookCopyId() {
        return bookCopyByBookCopyId;
    }

    public void setBookCopyByBookCopyId(BookCopy bookCopyByBookCopyId) {
        this.bookCopyByBookCopyId = bookCopyByBookCopyId;
    }
}

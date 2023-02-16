package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "book", schema = "library", catalog = "library")
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "isbn", nullable = false)
    private Long isbn;
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "book_uuid", nullable = false)
    private UUID bookUuid;
    @Basic
    @Column(name = "author", nullable = false, length = -1)
    private String author;
    @Basic
    @Column(name = "title", nullable = false, length = -1)
    private String title;
    @Basic
    @Column(name = "pages", nullable = false)
    private Long pages;
    @Basic
    @Column(name = "release_year", nullable = false)
    private Long releaseYear;
    @OneToMany(mappedBy = "bookByBookId")
    private Collection<BookCopy> bookCopiesById;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public UUID getBookUuid() {
        return bookUuid;
    }

    public void setBookUuid(UUID bookUuid) {
        this.bookUuid = bookUuid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Long releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Book() {
    }

    public Book(Long isbn, String author, String title, Long pages, Long releaseYear, Collection<BookCopy> bookCopiesById) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.pages = pages;
        this.releaseYear = releaseYear;
        this.bookCopiesById = bookCopiesById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return id.equals(that.id) && isbn.equals(that.isbn) && bookUuid.equals(that.bookUuid) && author.equals(that.author) && title.equals(that.title) && pages.equals(that.pages) && releaseYear.equals(that.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, bookUuid, author, title, pages, releaseYear);
    }

    public Collection<BookCopy> getBookCopiesById() {
        return bookCopiesById;
    }

    public void setBookCopiesById(Collection<BookCopy> bookCopiesById) {
        this.bookCopiesById = bookCopiesById;
    }
}

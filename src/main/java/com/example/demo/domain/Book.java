package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name= "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String country;
    private String imageLink;
    private String language;
    private String link;
    private Long pages;
    private String title;
    private Long year;

    public Book(String author, String country, String imageLink, String language, String link, Long pages, String title, Long year) {
        this.author = author;
        this.country = country;
        this.imageLink = imageLink;
        this.language = language;
        this.link = link;
        this.pages = pages;
        this.title = title;
        this.year = year;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long yearOfRelease) {
        this.year = yearOfRelease;
    }

    @Override
        public String toString() {
            return "Author: " + author +
                    ", Country: " + country +
                    ", Image Link: " + imageLink +
                    ", Language: " + language +
                    ", Link: " + link + ", "+
                    "Pages: " + pages +
                    ", Title: " + title +
                    ", Year: " + year + "\n";
        }

}

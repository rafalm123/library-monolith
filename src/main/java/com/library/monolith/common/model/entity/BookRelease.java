package com.library.monolith.common.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name="book_release")
public class BookRelease extends BaseEntity{

    @Column(name="isbn")
    private Long isbn;
    @Column(name="pages")
    private Integer pages;
    @Column(name="release_year")
    private Integer releaseYear;
    @Column(name="language")
    private String language;
    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    private Book book;
    @OneToMany(mappedBy = "bookRelease",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ReleaseCopy> copies;


}

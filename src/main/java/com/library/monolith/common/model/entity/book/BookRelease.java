package com.library.monolith.common.model.entity.book;

import com.library.monolith.common.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name="book_release")
public class BookRelease extends BaseEntity {

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
    private List<ReleaseCopy> releaseCopies;


}

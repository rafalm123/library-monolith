package com.library.monolith.common.model.entity.book;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@SequenceGenerator(name = "entiy_id_seq", sequenceName = "entiy_id_seq", allocationSize = 10)
public class BookRelease {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entiy_id_seq")
    private Long id;

    @Column(name = "isbn")
    private Long isbn;
    @Column(name = "pages")
    private Integer pages;
    @Column(name = "release_year")
    private Integer releaseYear;
    @Column(name = "language")
    private String language;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
    @OneToMany(mappedBy = "bookRelease", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReleaseCopy> releaseCopies;


}

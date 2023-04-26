package com.library.monolith.common.model.entity.book;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "entiy_id_seq", sequenceName = "entiy_id_seq", allocationSize = 10)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"author", "title"}))
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entiy_id_seq")
    private Long id;

    @Column(name = "author")
    private String author;
    @Column(name = "title")
    private String title;
    @Column(name = "publish_year")
    private Integer publishYear;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<BookRelease> bookReleases;

}

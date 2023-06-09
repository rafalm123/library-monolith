package com.library.monolith.common.model.entity.book;

import com.library.monolith.common.model.entity.BaseEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "book", uniqueConstraints = @UniqueConstraint(columnNames = {"author", "title"}))
public class Book extends BaseEntity {

    @Column(name="author")
    private String author;
    @Column(name="title")
    private String title;
    @Column(name = "publish_year")
    private Integer publishYear;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<BookRelease> bookReleases;

}

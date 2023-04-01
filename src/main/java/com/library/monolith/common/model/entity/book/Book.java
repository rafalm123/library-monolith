package com.library.monolith.common.model.entity.book;

import com.library.monolith.common.model.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "book")
public class Book extends BaseEntity {

    @Column(name="author")
    private String author;
    @Column(name="title")
    private String title;
    @Column(name = "publish_year")
    private Integer publishYear;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookRelease> bookReleases;
}

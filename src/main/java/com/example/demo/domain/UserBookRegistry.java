package com.example.demo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user_book_registry", schema = "library", catalog = "library")
public class UserBookRegistry {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "user_id", nullable = true, insertable = false, updatable = false)
    private Long userId;
    @Basic
    @Column(name = "book_copy_id", nullable = true, insertable = false, updatable = false)
    private Long bookCopyId;
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "registry_uuid", nullable = false)
    private UUID registryUuid;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "finish_at", nullable = false)
    private Timestamp finishAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(Long bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public UUID getRegistryUuid() {
        return registryUuid;
    }

    public void setRegistryUuid(UUID registryUuid) {
        this.registryUuid = registryUuid;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(Timestamp finishAt) {
        this.finishAt = finishAt;
    }

    public UserBookRegistry(Timestamp createdAt, Timestamp finishAt) {
        this.createdAt = createdAt;
        this.finishAt = finishAt;
    }

    public UserBookRegistry() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBookRegistry that = (UserBookRegistry) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(bookCopyId, that.bookCopyId)
                && Objects.equals(registryUuid, that.registryUuid) && Objects.equals(createdAt, that.createdAt) && Objects.equals(finishAt, that.finishAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, bookCopyId, registryUuid, createdAt, finishAt);
    }
}

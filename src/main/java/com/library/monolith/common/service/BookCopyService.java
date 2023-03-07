package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.ReleaseCopyEntity;
import com.library.monolith.common.repository.BookReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookCopyService {

    private final BookReleaseRepository bookReleaseRepository;

    @Autowired
    public BookCopyService(BookReleaseRepository bookReleaseRepository) {
        this.bookReleaseRepository = bookReleaseRepository;
    }

    public List<ReleaseCopyEntity> listCopies(){
        return bookReleaseRepository.findAll();
    }

    public Optional<ReleaseCopyEntity> getCopyById(long id) {
        return listCopies().stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    public Optional<ReleaseCopyEntity> getCopyByUuid(UUID uuid) {
        return listCopies().stream()
                .filter(book -> book.getBookCopyUuid().equals(uuid))
                .findFirst();
    }

    public ReleaseCopyEntity saveCopy(ReleaseCopyEntity releaseCopyEntity){
        return bookReleaseRepository.save(releaseCopyEntity);
    }

    public void removeCopy(ReleaseCopyEntity releaseCopyEntity){
        bookReleaseRepository.delete(releaseCopyEntity);
    }
}

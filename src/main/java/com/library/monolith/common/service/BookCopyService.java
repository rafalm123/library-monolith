package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.ReleaseCopyEntity;
import com.library.monolith.common.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;

    @Autowired
    public BookCopyService(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    public List<ReleaseCopyEntity> listCopies(){
        return bookCopyRepository.findAll();
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
        return bookCopyRepository.save(releaseCopyEntity);
    }

    public void removeCopy(ReleaseCopyEntity releaseCopyEntity){
        bookCopyRepository.delete(releaseCopyEntity);
    }
}

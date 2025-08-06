package com.muun.library.service;

import com.muun.library.domain.Book;
import com.muun.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }


    /*
     * 회원 가입
     * */
    public Long join(Book book) {
        //중복 도서 방지
        validateDuplicateBook(book);

        repository.save(book);
        return book.getId();
    }

    private void validateDuplicateBook(Book book) {
        repository.findByName(book.getTitle())
                .ifPresent(b ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }


        //전체 도서 조회
        public List<Book> findBooks() {
            return repository.findAll();
        }


        /*
         * 단일 도서 조회
         * */
        public Optional<Book> findOne(Long bookId) {
        return repository.findById(bookId);
    }
}

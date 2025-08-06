package com.muun.library.service;

import com.muun.library.domain.Book;
import com.muun.library.repository.MemoryBookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    MemoryBookRepository repository;
    BookService bookService;

    @BeforeEach
    public void beforeEach() {
    repository = new MemoryBookRepository();
    bookService = new BookService(repository);
}
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Book book = new Book();
        book.setTitle("book");

        //when
        Long saveId = bookService.join(book);

        //then
        Book findBook = bookService.findOne(saveId).get();
        assertThat(book.getTitle()).isEqualTo(findBook.getTitle());

    }

    @Test
    void 중복_회원_예외() {
        //given
        Book book1 = new Book();
        book1.setTitle("book");

        Book book2 = new Book();
        book2.setTitle("book");

        //when
        bookService.join(book1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> bookService.join(book2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        //1.중복 회원 테스트
//        try {
//            bookService.join(book2);
//            Assertions.fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.213");
//        }
    }

    @Test
    void findAll() {
        Book book1 = new Book();
        book1.setTitle("book1");
        repository.save(book1);


        Book book2 = new Book();
        book2.setTitle("book2");
        repository.save(book2);

        List<Book> books = bookService.findBooks();

//        assertThat(books).hasSize(2);
        assertThat(books).extracting("title")
                .containsExactlyInAnyOrder("book1", "book2");
    }

}
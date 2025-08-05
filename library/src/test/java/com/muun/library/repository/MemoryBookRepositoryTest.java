package com.muun.library.repository;

import com.muun.library.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryBookRepositoryTest {

    MemoryBookRepository repository = new MemoryBookRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    void save() {
        Book book = new Book();
        book.setTitle("book");

        repository.save(book);

        Book result = repository.findById(book.getId()).get();
//        Assertions.assertEquals(book, null);
        assertThat(book).isEqualTo(result);

    }

    @Test
    void findByName() {
        Book book1 = new Book();
        book1.setTitle("book1");
        repository.save(book1);

        Book book2 = new Book();
        book2.setTitle("book2");
        repository.save(book2);

        Book result = repository.findByName("book1").get();
        Assertions.assertThat(result).isEqualTo(book1);

    }

    @Test
    void findAll() {
        Book book1 = new Book();
        book1.setTitle("book1");
        repository.save(book1);

        Book book2 = new Book();
        book2.setTitle("book2");
        repository.save(book2);

        List<Book> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }




    @Test
    void findById() {
    }
}
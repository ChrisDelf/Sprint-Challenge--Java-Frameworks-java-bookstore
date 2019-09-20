package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;

import java.awt.print.Pageable;
import java.util.List;

public interface BookService {
    List<Book> findAll(Pageable pagable);

    List<Book> findAll();

    Book save(Book book);

    Book findById(long id);

    Book update(Book book, long id);

    void delete(long id);

}
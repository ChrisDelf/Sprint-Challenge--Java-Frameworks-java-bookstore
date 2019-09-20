package com.lambdaschool.starthere.services;


import com.lambdaschool.starthere.models.Book;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService{
    @Override
    public List<Book> findAll(Pageable pagable) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public Book findById(long id) {
        return null;
    }

    @Override
    public Book update(Book book, long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}

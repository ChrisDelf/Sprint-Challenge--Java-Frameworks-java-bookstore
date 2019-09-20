package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public Author findAuthorById(long id) {
        return null;
    }
}

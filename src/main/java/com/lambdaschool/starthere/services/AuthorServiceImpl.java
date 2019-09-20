package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    private AuthorRepository authorrepo;

    @Override
    public List<Author> findAll() {
        List<Author> aList = new ArrayList<>();
        authorrepo.findAll().iterator().forEachRemaining(aList::add);
        return aList;
    }
    @Override
    public Author findAuthorById(long id) {
        return authorrepo.findById(id).orElseThrow(()
                ->new ResourceNotFoundException(Long.toString(id)));
    }
}

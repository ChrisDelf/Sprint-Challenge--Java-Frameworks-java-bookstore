package com.lambdaschool.starthere.services;


import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService{
    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookRepository bookRepo;

    @Override
    public List<Book> findAll(Pageable pagable) {
        List<Book> bList = new ArrayList<>();
        bookRepo.findAll().iterator().forEachRemaining(bList :: add);
        return bList;
    }

    @Override
    public List<Book> findAll() {
        List<Book> bList = new ArrayList<>();
        bookRepo.findAll().iterator().forEachRemaining(bList :: add);
        return bList;
    }

    @Transactional
    @Override
    public Book save(Book book)
    {
        Book newBook = new Book();

        newBook.setTitle(book.getTitle());
        newBook.setCopy(book.getCopy());
        newBook.setISBN(book.getISBN());

        ArrayList<Author> newAuthor = new ArrayList<>();
        for (Author a : book.getAuthors())
        {
            newAuthor.add(new Author(a.getLname(), a.getFname()));
        }
        newBook.setAuthors(newAuthor);

        return bookRepo.save(newBook);
    }


    @Transactional
    @Override
    public Book update(Book book, long id) {
        Book currentBook = bookRepo
                .findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
        if (book.getTitle() != null) {
            if (book.getTitle() != null) {
                currentBook.setTitle(book.getTitle());
            }

            if (book.getISBN() != null) {
                currentBook.setISBN(book.getISBN());
            }

            if (book.getCopy() != 0) {
                currentBook.setCopy(book.getCopy());
            }
        }
        return bookRepo.save(currentBook);
    }

    @Override
    public Book findBookById(long id) {
        return bookRepo.findById(id).orElseThrow(()
                ->new ResourceNotFoundException(Long.toString(id)));
    }

    @Transactional
    @Override
    public void delete(long id) throws ResourceNotFoundException
    {
        if (bookRepo.findById(id).isPresent())
        {
            bookRepo.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Book updateBookToAuthor(long bookid, long authorid)
    {
        Book currentBooks = bookRepo.findByBookid(bookid);
        Author currentAuthors = authorRepo.findByAuthorid(authorid);

        currentBooks.getAuthors().add(currentAuthors);

        return currentBooks;
    }
}

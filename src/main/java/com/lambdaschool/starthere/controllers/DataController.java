package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.AuthorService;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;


    // get localhost:9000/data/books
    @ApiOperation(value = "Returns all books",responseContainer = "List")
    @GetMapping(value = "/books",
            produces={"application/json"})
    public ResponseEntity<?> allBooks(){
        List<Book> res=bookService.findAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

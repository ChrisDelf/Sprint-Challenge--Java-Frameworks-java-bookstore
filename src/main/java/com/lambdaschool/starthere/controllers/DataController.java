package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.AuthorService;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import javax.swing.plaf.ColorUIResource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;





    // get localhost:9000/data/books

    @ApiOperation(value = "Lists all books", responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "page",
                    dataType = "integer",
                    paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(
                    name = "size",
                    dataType = "integer",
                    paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(
                    name = "sort",
                    allowMultiple = true,
                    dataType = "string",
                    paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book(s) Found", responseContainer = "List", response = Book.class),
            @ApiResponse(code = 404, message = "Books Not Found", response = ErrorDetail.class)})
    @GetMapping(value = "/books",
            produces={"application/json"})
    public ResponseEntity<?> allBooks(){
        List<Book> res=bookService.findAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    // get localhost:9000/data/authors
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "page",
                    dataType = "integer",
                    paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(
                    name = "size",
                    dataType = "integer",
                    paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(
                    name = "sort",
                    allowMultiple = true,
                    dataType = "string",
                    paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Authors(s) Found", responseContainer = "List", response = Author.class),
            @ApiResponse(code = 404, message = "Author Not Found", response = ErrorDetail.class)})

    @ApiOperation(value = "Returns all authors",
            responseContainer = "List")

    @GetMapping(value = "/authors",
            produces = {"application/json"})
    public ResponseEntity<?> allAuthors(){
        List<Author> res=authorService.findAll();
        return new ResponseEntity<>(res,HttpStatus.OK);
    }


    // Put localhost:9000/data/books/1
    @ApiOperation(value = "Updates book id with body book", consumes = "Book",response=void.class)
    @ApiResponses(value = {
            @ApiResponse(code=404,message = "Book Not Found",response = ErrorDetail.class),
            @ApiResponse(code=500, message="Error", response = ErrorDetail.class)
    })
    @PutMapping(value = "/books/{id}",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<?> updateBook(@PathVariable long id,
                                        @RequestBody
                                        @Valid Book book){
        Book res = bookService.update(book,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // Post localhost:9000/data/books/1/authors/1

    @ApiOperation(value = "Create a book")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Error Creating Book", response = ErrorDetail.class),
            @ApiResponse(code=201,message = "Book Created", response=void.class)
    })
    @PostMapping(value = "/books/{bookid}/authors/{authorid}")
    public ResponseEntity<?> updateBookToAuthor(
            @ApiParam(value = "book id", example = "1")
            @PathVariable long bookid,
            @ApiParam(value = "author id", example = "1")
            @PathVariable long authorid)
    {

        Book newBook = bookService.updateBookToAuthor(bookid, authorid);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
// Delete  localhost:9000/data/books/1

    @ApiOperation(value = "Delete a book")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Error Creating Book", response = ErrorDetail.class),
            @ApiResponse(code=404,message="Book Not Found", response = ErrorDetail.class)
    })

    @DeleteMapping(value = "/books/{bookid}")
    public ResponseEntity<?> deleteBookById(

            @PathVariable long bookid)
    {
        bookService.delete(bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

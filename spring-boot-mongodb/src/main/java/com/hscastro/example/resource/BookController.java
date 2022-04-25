package com.hscastro.example.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hscastro.example.entities.Book;
import com.hscastro.example.services.BookService;

@RestController
@RequestMapping(value = "/api/v1")
public class BookController {

	
	@Autowired
	private BookService bookService;
	
	@GetMapping(value = "/hello")
	public String getMessage(){
		return "Hello, endpoint working...";
	}
	
	@GetMapping(value = "/books")
	public ResponseEntity<List<Book>> getAllBook(){
		List<Book> listBooks = bookService.findAllBooks();
		return ResponseEntity.ok(listBooks);
	}
	
	//@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/books")
	public ResponseEntity<Book> createBook(@RequestBody Book book){
		Book createBook = bookService.saveBooks(book);
		return new ResponseEntity<>(createBook, HttpStatus.CREATED);
	}
}

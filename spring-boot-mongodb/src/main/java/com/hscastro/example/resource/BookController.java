package com.hscastro.example.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping(value = "/books/{id}")
	public ResponseEntity<Optional<Book>> getBookById(@PathVariable("id") String id){
		Optional<Book> book = bookService.findBook(id);
		if(book.isPresent()) {
			return ResponseEntity.ok(book);
		}
		throw new RuntimeException("Book não encontrado!");		
	}
	
	//@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/books")
	public ResponseEntity<Book> createBook(@RequestBody Book book){
		Book createBook = bookService.saveBooks(book);
		return new ResponseEntity<>(createBook, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") String id, @RequestBody Book book){
		Optional<Book> bookId = bookService.findBook(id);
		
		if(bookId.isPresent()) {
			Book book_ = bookId.get();
			book_.setId(book.getId());
			book_.setTitle(book.getTitle());
			book_.setAuthor(book.getAuthor());
			book_.setIsbn(book.getIsbn());
			book_ = bookService.saveBooks(book);
			
			return new ResponseEntity<>(book_, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
}

package com.hscastro.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hscastro.example.entities.Book;
import com.hscastro.example.reppositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAllBooks(){
		return bookRepository.findAll();	
	}
	
	public Optional<Book> findBook(String id){
		return bookRepository.findById(id);	
	}
	
	public Book saveBook(Book book){
		return bookRepository.save(book);	
	}
	
	public void deleteBook(String id){
		bookRepository.deleteById(id);	
	}
}

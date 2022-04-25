package com.hscastro.example.reppositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.hscastro.example.entities.Book;

@EnableMongoRepositories
public interface BookRepository extends MongoRepository<Book, String> {
}

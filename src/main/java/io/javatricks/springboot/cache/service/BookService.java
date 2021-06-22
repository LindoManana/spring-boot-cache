package io.javatricks.springboot.cache.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.javatricks.springboot.cache.entities.Book;
import io.javatricks.springboot.cache.repository.BookRepository;
import o.javatricks.springboot.cache.exception.ResourceNotFoundException;

@Service
public class BookService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

	@Autowired
	private BookRepository bookRepository;

	@CacheEvict(cacheNames = "books", allEntries = true)
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Cacheable(cacheNames = "books", sync = true)
	public List<Book> findAll() {
		LOGGER.info("@@ not cached, thus getting data from to db...");
		return bookRepository.findAll();
	}

	@Cacheable(cacheNames = "books", key = "#id", sync = true)
	public Book findById(Long id) throws ResourceNotFoundException {
		LOGGER.info("@@ not cached, thus getting data from to db...");
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :: " + id));
		return book;
	}

	// @CachePut(cacheNames = "books", key = "#book.id")
	@CacheEvict(cacheNames = "books", allEntries = true)
	public Book update(Book book) throws ResourceNotFoundException {

		findById(book.getId());

		return bookRepository.save(book);
	}

	//@CacheEvict(cacheNames = "books", key = "#id")
	@CacheEvict(cacheNames = "books", allEntries = true)
	public String delete(Long id) {
		Book book = findById(id);
		bookRepository.delete(book);
		String msg = "Book deleted successfully";
		return msg;
	}

}

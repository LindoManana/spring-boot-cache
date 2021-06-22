package io.javatricks.springboot.cache.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import io.javatricks.springboot.cache.entities.Book;
import io.javatricks.springboot.cache.service.BookService;
import o.javatricks.springboot.cache.exception.ResourceNotFoundException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	@PostMapping("/books")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody Book book) {

		LOGGER.info("@@ request => " + new Gson().toJson(book));

		return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
	}

	@GetMapping("/books/{id}")
	public @ResponseBody ResponseEntity<?> findById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {

		LOGGER.info("@@ request => " + id);

		Book book = bookService.findById(id);
		return new ResponseEntity<>(book, HttpStatus.OK);

	}

	@GetMapping("/books")
	public @ResponseBody ResponseEntity<?> findAll() {

		return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
	}

	@PutMapping("/books")
	public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody Book book) throws ResourceNotFoundException {

		LOGGER.info("@@ request => " + new Gson().toJson(book));

		return new ResponseEntity<>(bookService.update(book), HttpStatus.OK);
	}

	@DeleteMapping("/books/{id}")
	public @ResponseBody ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

		LOGGER.info("@@ request => " + id);

		return new ResponseEntity<>(bookService.delete(id), HttpStatus.OK);
	}
}

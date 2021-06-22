package io.javatricks.springboot.cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.javatricks.springboot.cache.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}

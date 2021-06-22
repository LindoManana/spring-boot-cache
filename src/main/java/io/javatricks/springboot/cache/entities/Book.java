package io.javatricks.springboot.cache.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = "name is required")
	@Column(name = "name")
	private String name;

	@NotBlank(message = "author is required")
	@Column(name = "author")
	private String author;

	@NotBlank(message = "edition is required")
	@Column(name = "edition")
	private String edition;
}

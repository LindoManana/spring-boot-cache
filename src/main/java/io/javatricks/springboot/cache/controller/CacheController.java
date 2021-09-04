package io.javatricks.springboot.cache.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.javatricks.springboot.cache.service.CacheService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CacheController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);

	@Autowired
	private CacheService cacheService;

	@GetMapping("/evict-all-caches")
	public @ResponseBody ResponseEntity<?> evictAllCaches() {
		LOGGER.info("@@ evict all caches...");
		cacheService.evictAllCaches();
		return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
	}

}

package com.authserver.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {

	@GetMapping("/")
	public ResponseEntity<Void> index() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}

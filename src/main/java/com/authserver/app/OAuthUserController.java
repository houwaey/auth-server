package com.authserver.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authserver.model.OAuthUser;
import com.authserver.service.UserService;

@RestController
@RequestMapping("/oauth/acct")
public class OAuthUserController {
	
	@Autowired
	private UserService<OAuthUser> oauthUserService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/user")
	public ResponseEntity<Void> createUser(@RequestBody OAuthUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		boolean created = oauthUserService.create(user);
		if (!created) {
			return new ResponseEntity<Void>(HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/user")
	public ResponseEntity<OAuthUser> updateUser(@RequestBody OAuthUser user) {
		if (!oauthUserService.exists(user.getId())) {
			return new ResponseEntity<OAuthUser>(HttpStatus.NOT_FOUND);
		}
		boolean updated = oauthUserService.update(user);
		if (!updated) {
			return new ResponseEntity<OAuthUser>(HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<OAuthUser>(user, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<OAuthUser> findUserById(@PathVariable("id") long id) {
		OAuthUser user = oauthUserService.findOneById(id);
		if (user == null) {
			return new ResponseEntity<OAuthUser>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OAuthUser>(user, HttpStatus.FOUND);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<OAuthUser>> findAllUsers() {
		List<OAuthUser> users = oauthUserService.findAll();
		if (users.size() <= 0) {
			return new ResponseEntity<List<OAuthUser>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<OAuthUser>>(users, HttpStatus.OK);
	}
	
}

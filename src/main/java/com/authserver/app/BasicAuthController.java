package com.authserver.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authserver.model.BasicAuthRole;
import com.authserver.model.BasicAuthUser;
import com.authserver.service.UserService;

@RestController
@RequestMapping("/basic-auth")
public class BasicAuthController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService<BasicAuthRole> basicAuthRoleService;
	
	@Autowired
	private UserService<BasicAuthUser> basicAuthUserService;
	
	@PostMapping("/role")
	public ResponseEntity<BasicAuthRole> addRole(@RequestBody BasicAuthRole role) {
		boolean created = basicAuthRoleService.create(role);
		if (!created) {
			return new ResponseEntity<BasicAuthRole>(role, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<BasicAuthRole>(role, HttpStatus.CREATED);
	}
	
	@GetMapping("/roles")
	public ResponseEntity<List<BasicAuthRole>> findAllRoles() {
		List<BasicAuthRole> roles = basicAuthRoleService.findAll();
		if (roles.size() <= 0) {
			return new ResponseEntity<List<BasicAuthRole>>(roles, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BasicAuthRole>>(roles, HttpStatus.OK);
	}
	
	
	/* ------------------------------------------------------------------- */
	
	@PostMapping("/user")
	public ResponseEntity<BasicAuthUser> addUser(@RequestBody BasicAuthUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		boolean created = basicAuthUserService.merge(user);
		if (!created) {
			return new ResponseEntity<BasicAuthUser>(user, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<BasicAuthUser>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<BasicAuthUser>> findAllUsers() {
		List<BasicAuthUser> users = basicAuthUserService.findAll();
		if (users.size() <= 0) {
			return new ResponseEntity<List<BasicAuthUser>>(users, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BasicAuthUser>>(users, HttpStatus.OK);
	}
	
}
package com.authserver.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authserver.dto.AccessToken;
import com.authserver.dto.AccessTokenRequest;
import com.authserver.dto.Token;
import com.authserver.exception.ForbiddenException;
import com.authserver.exception.InternalServerException;
import com.authserver.exception.UnauthorizedException;
import com.authserver.model.OAuthUser;
import com.authserver.service.OAuthService;

@RestController
@RequestMapping("/oauth/token")
public class OAuthController {
	
	@Autowired
	private OAuthService<OAuthUser> oauthCoreService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/grant-type/password")
	public ResponseEntity<Token> requestAccessToken(@RequestBody AccessTokenRequest request) {
		OAuthUser user = oauthCoreService.findOneByKeyVal("username", request.getUsername());
		if (user == null) {
			throw new UnauthorizedException("Username does not exists");
		}
		
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new ForbiddenException("Invalid password");
		}
		
		user = oauthCoreService.generateAccessToken(user);
		if (user == null) {
			throw new InternalServerException("Unable to generate access token");
		}
		
		long expires_in = (user.getTokenExpiration().getTime() / 1000) - (user.getTimestamp().getTime() / 1000);
		
		return new ResponseEntity<Token>(
					new Token(user.getAccessToken(), user.getTokenType(), expires_in)
					, HttpStatus.OK);
	}
	
	@GetMapping("/grant-type/client-credentials/{token}")
	public ResponseEntity<Token> checkTokenValidity(@PathVariable("token") String token) {
		AccessToken accessToken = oauthCoreService.initToken(token);
		if (!accessToken.isTokenValid()) {
			throw new UnauthorizedException("Invalid token");
		}
		
		if (!accessToken.isTokenNonExpired()) {
			throw new ForbiddenException("Token is expired");
		}
		
		return new ResponseEntity<Token>(accessToken.getTokenObject(), HttpStatus.OK);
	}
	
}
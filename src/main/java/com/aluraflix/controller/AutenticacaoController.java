package com.aluraflix.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluraflix.controller.dto.TokenDto;
import com.aluraflix.controller.form.AutenticacaoForm;
import com.aluraflix.service.TokenService;

@RestController
@RequestMapping("/videos/auth")
public class AutenticacaoController {
	
	private AuthenticationManager authManager;
	
	private TokenService tokenService;
	
	public AutenticacaoController(AuthenticationManager authManager, TokenService tokenService) {
		this.authManager = authManager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid AutenticacaoForm form) {
		
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}

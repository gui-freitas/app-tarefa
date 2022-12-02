package com.viceri.tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viceri.tarefas.entity.Usuario;
import com.viceri.tarefas.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;


	@PostMapping("/cadastro")
	public ResponseEntity<String> inserirUsuario(@RequestBody Usuario usuario) {
		try {
			usuarioService.inserirUsuario(usuario);
			return new ResponseEntity<>("Usuario cadastrado com sucesso!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
package com.viceri.tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viceri.tarefas.entity.Usuario;
import com.viceri.tarefas.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void inserirUsuario(Usuario usuario) {
		usuarioRepository.inserirUsuario(usuario);
	}
}
package com.viceri.tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.viceri.tarefas.entity.Usuario;
import com.viceri.tarefas.repository.UsuarioRepository;
import com.viceri.tarefas.service.exceptions.EmailCadastradoException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void inserirUsuario(Usuario usuario) {
		boolean jaExisteEmailCadastrado = usuarioRepository.consultarPorEmail(usuario.getEmail());
		
		if(jaExisteEmailCadastrado) {
			throw new EmailCadastradoException(usuario.getEmail());
		}
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		usuarioRepository.inserirUsuario(usuario);
	}
}
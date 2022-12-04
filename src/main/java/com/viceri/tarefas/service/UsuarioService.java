package com.viceri.tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.viceri.tarefas.entity.Usuario;
import com.viceri.tarefas.repository.UsuarioRepository;
import com.viceri.tarefas.service.exceptions.EmailCadastradoException;
import com.viceri.tarefas.service.exceptions.SenhaInvalidaException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void inserirUsuario(Usuario usuario) {
		verificarSegurancaSenha(usuario.getSenha());
		
		boolean jaExisteEmailCadastrado = usuarioRepository.consultarPorEmail(usuario.getEmail());
		
		if(jaExisteEmailCadastrado) {
			throw new EmailCadastradoException(usuario.getEmail());
		}
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		usuarioRepository.inserirUsuario(usuario);
	}

	private void verificarSegurancaSenha(String senha) {
		String msg = """
				Senha não corresponde aos requisitos mínimos de segurança. 
				A senha deve conter letras maiúsculas, minúsculas, números \
				e pelo menos 1 caractere especial EX:!@#$%&*”
				""";
		
		 String alfabeto = "(.*[A-Z].*)";
		 String numeros = "(.*[0-9].*)";
		 String caracteresEspeciais = "(.*[!,@,#,$,%,&,*].*)";
		
		if(!(senha.matches(alfabeto) 
				&& senha.matches(alfabeto.toLowerCase())
				&& senha.matches(numeros)
				&& senha.matches(caracteresEspeciais))) {
			throw new SenhaInvalidaException(msg);
		}
	}
}
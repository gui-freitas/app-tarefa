package com.viceri.tarefas.service;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.viceri.tarefas.entity.Usuario;
import com.viceri.tarefas.service.exceptions.SenhaInvalidaException;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UsuarioServiceTest {
	
	@Autowired
	private UsuarioService usuarioService = mock(UsuarioService.class);

	
	@Test(expected = SenhaInvalidaException.class)
	public void inserirUsuarioSenhaInvalida() {
		
		Usuario usuario = new Usuario(null, "Teste", "Teste@email.com", "test3!");
		usuarioService.inserirUsuario(usuario);
	}
	
	@Test(expected = SenhaInvalidaException.class)
	public void inserirUsuarioSenhaValida() {
		
		Usuario usuario = new Usuario(null, "Teste", "Teste@email.com", "!@#1Aa");
		
		usuarioService.inserirUsuario(usuario);
	}
}

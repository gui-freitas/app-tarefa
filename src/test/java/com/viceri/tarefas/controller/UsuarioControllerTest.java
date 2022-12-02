package com.viceri.tarefas.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viceri.tarefas.entity.Usuario;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UsuarioControllerTest {
	
	private final static String URL = "/api/usuarios";
	
	@Autowired
	private MockMvc mockMvc;
	
    @Autowired
    private ObjectMapper objectMapper;
	
	@Test
	public void testInserirUsuario() throws Exception {
		Usuario usuario = new Usuario(null, "Novo Teste", "Teste@email.com", "SenhaSemCripto");
		
		mockMvc.perform(post(URL + "/cadastro")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(usuario)))
				.andExpect(status().isCreated());
	}
}
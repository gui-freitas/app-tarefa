package com.viceri.tarefas.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.viceri.tarefas.entity.dto.NovaTarefaDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TarefaControllerTest {
	
	private final static String URL = "/api/tarefas";
	
	@Autowired
	private MockMvc mockMvc;
	
    @Autowired
    private ObjectMapper objectMapper;
	
	@Test
	public void testInserirTarefa() throws Exception {
		NovaTarefaDTO tarefa01 = new NovaTarefaDTO("Estudar", "Media", "Concluida");
		
		mockMvc.perform(post(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(tarefa01)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void testDeletarTarefa() throws Exception {
		testInserirTarefa();
		int id = 1;
		
		mockMvc.perform(delete(URL + "/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(id)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testAtualizarTarefa() throws Exception {
		testInserirTarefa();
		int id = 2;
		NovaTarefaDTO tarefa02 = new NovaTarefaDTO("Trabalhar", "Alta", "Pendente");
		
		mockMvc.perform(put(URL + "/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(tarefa02)))
				.andExpect(status().isNoContent());
	}

}

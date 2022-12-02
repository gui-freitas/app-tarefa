package com.viceri.tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viceri.tarefas.entity.Tarefa;
import com.viceri.tarefas.entity.dto.TarefaDTO;
import com.viceri.tarefas.service.TarefaService;
import com.viceri.tarefas.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;

	@GetMapping
	public ResponseEntity<Page<Tarefa>> listarTarefas() {
		return ResponseEntity.ok().body(tarefaService.listarTarefasPorStatus());
	}

	@PostMapping
	public ResponseEntity<String> inserirTarefa(@RequestBody TarefaDTO tarefa) {
		try {
			tarefaService.inserirTarefa(tarefa);
			return new ResponseEntity<>("Tarefa criada com sucesso!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletarTarefa(@PathVariable Integer id) {
		try {
			tarefaService.deletarTarefa(id);
			return new ResponseEntity<>("Tarefa deletada com sucesso", HttpStatus.OK);
		  } catch (ObjectNotFoundException e) { 
			  return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		  }catch (Exception e) {
			  return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		  } 
	}

	  @PutMapping(value = "/{id}") 
	  public ResponseEntity<String> atualizarTarefa(@RequestBody TarefaDTO tarefaDto, @PathVariable Integer id){ 
		  try {
			  tarefaService.atualizarTarefa(tarefaDto, id); 
			  return new ResponseEntity<>("Tarefa atualizada com sucesso!", HttpStatus.NO_CONTENT); 
		  } catch (ObjectNotFoundException e) { 
			  return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		  }catch (Exception e) {
			  return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		  } 
	  }
}
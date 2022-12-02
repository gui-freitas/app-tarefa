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
			return new ResponseEntity<>("Erro ao inserir", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletarTarefa(@PathVariable Integer id) {
		try {
			tarefaService.deletarTarefa(id);
			return new ResponseEntity<>("Tarefa deletada com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Tarefa não encontrada", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarTarefaPorId(@PathVariable Integer id) {
		try {
			return new ResponseEntity<Tarefa>(tarefaService.buscarTarefaPorId(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Tarefa não encontrada", HttpStatus.NOT_FOUND);
		}
	}

	  @PutMapping(value = "/{id}") 
	  public ResponseEntity<String> atualizarTarefa(@RequestBody TarefaDTO tarefaDto, @PathVariable Integer id){ 
		  try {
			  Tarefa tarefa = tarefaService.buscarTarefaPorId(id);
			  if (tarefa != null) {
				  tarefaService.atualizarTarefa(tarefaDto, id); 
				  return new ResponseEntity<>("Tarefa atualizada com sucesso!", HttpStatus.CREATED); 
			  } else {
				  return new ResponseEntity<>("Não pode encontrar uma tarefa com o id: " + id, HttpStatus.NOT_FOUND);
			  }
		  } catch (Exception e) {
			  return new ResponseEntity<>("Erro ao tentar atualizar", HttpStatus.INTERNAL_SERVER_ERROR);
		  } 
	  }
}
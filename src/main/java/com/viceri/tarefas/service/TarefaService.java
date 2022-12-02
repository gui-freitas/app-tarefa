package com.viceri.tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.viceri.tarefas.entity.Tarefa;
import com.viceri.tarefas.entity.dto.TarefaDTO;
import com.viceri.tarefas.entity.enums.Prioridade;
import com.viceri.tarefas.entity.enums.Status;
import com.viceri.tarefas.repository.TarefaRepository;
import com.viceri.tarefas.service.exceptions.ObjectNotFoundException;

@Service
public class TarefaService {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	public Page<Tarefa> listarTarefasPorStatus(){
		return tarefaRepository.listarTarefasPorStatus(Status.PENDENTE);
	}

	public void inserirTarefa(TarefaDTO tarefa) {
		tarefaRepository.inserirTarefa(converterDeDTO(tarefa));
	}
	
	public Tarefa converterDeDTO(TarefaDTO tarefa) {
		return new Tarefa(
				null,
				tarefa.descricao(), 
				Prioridade.toEnum(tarefa.prioridade()).getCodigo(), 
				Status.toEnum(tarefa.status()).getCodigo());
	}
	
	public void deletarTarefa(Integer id) {
		buscarTarefaPorId(id);
		tarefaRepository.deletarTarefaPorId(id);
	}

	public Tarefa buscarTarefaPorId(Integer id){
		Tarefa resultado = tarefaRepository.buscarTarefaPorId(id);
		if (resultado == null) {
			throw new ObjectNotFoundException("Tarefa não encontrada. Id: " + id);
		}
		return resultado;
	}

	public void atualizarTarefa(TarefaDTO tarefa, Integer id) {
		buscarTarefaPorId(id);
		tarefaRepository.atualizarTarefa(converterDeDTO(tarefa), id);
	}
}
package com.viceri.tarefas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.viceri.tarefas.entity.Tarefa;
import com.viceri.tarefas.entity.dto.NovaTarefaDTO;
import com.viceri.tarefas.entity.dto.TarefaDTO;
import com.viceri.tarefas.entity.enums.Prioridade;
import com.viceri.tarefas.entity.enums.Status;
import com.viceri.tarefas.repository.TarefaRepository;
import com.viceri.tarefas.service.exceptions.ObjectNotFoundException;

@Service
public class TarefaService {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	public Page<TarefaDTO> listarTarefasPorStatus(){
		List<Tarefa> tarefas = tarefaRepository.listarTarefasPorStatus(Status.PENDENTE);
		List<TarefaDTO> tarefasDto = new ArrayList<>();
		for(Tarefa tarefa : tarefas) {
			tarefasDto.add(converterParaDTO(tarefa));
		}
		return new PageImpl<TarefaDTO>(tarefasDto);
	}

	public void inserirTarefa(NovaTarefaDTO tarefa) {
		tarefaRepository.inserirTarefa(converterDeDTO(tarefa));
	}
	
	public Tarefa converterDeDTO(NovaTarefaDTO tarefa) {
		return new Tarefa(
				null,
				tarefa.descricao(), 
				Prioridade.toEnum(tarefa.prioridade()).getCodigo(), 
				Status.toEnum(tarefa.status()).getCodigo());
	}
	
	public TarefaDTO converterParaDTO(Tarefa tarefa) {
		return new TarefaDTO(
				tarefa.getId(),
				tarefa.getDescricao(), 
				Prioridade.toEnum(tarefa.getPrioridade()).getDescricao(), 
				Status.toEnum(tarefa.getStatus()).getDescricao());
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

	public void atualizarTarefa(NovaTarefaDTO tarefa, Integer id) {
		buscarTarefaPorId(id);
		tarefaRepository.atualizarTarefa(converterDeDTO(tarefa), id);
	}
}
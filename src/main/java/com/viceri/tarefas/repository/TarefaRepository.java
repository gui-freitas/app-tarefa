package com.viceri.tarefas.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.viceri.tarefas.entity.Tarefa;
import com.viceri.tarefas.entity.enums.Status;

@Repository
public class TarefaRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Tarefa> listarTarefasPorStatus(Status pendente) {
		String sql = """
				SELECT * 
				FROM tarefa 
				WHERE status = ?
				""";
		
		return jdbcTemplate.query(
				sql, (rs, rowNum) -> new Tarefa(rs.getInt("id"), 
						rs.getString("descricao"), 
						rs.getInt("prioridade"), 
						rs.getInt("status")),
				pendente.getCodigo());
	}

	public int inserirTarefa(Tarefa tarefa) {
		String sql = """
				INSERT INTO tarefa (descricao, prioridade, status)
				VALUES (?, ?, ?)
				""";
		
		return jdbcTemplate.update(sql, tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.getStatus());
	}

	public Tarefa buscarTarefaPorId(Integer id) {
		String sql = """
				SELECT * 
				FROM tarefa 
				WHERE id = ?
				""";
		
		try {
		return jdbcTemplate.queryForObject(
				sql, (rs, rowNum) -> new Tarefa(rs.getInt("id"), 
						rs.getString("descricao"), 
						rs.getInt("prioridade"), 
						rs.getInt("status")),
				id);
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	public int deletarTarefaPorId(Integer id) {
		String sql = """
				DELETE FROM tarefa 
				WHERE id = ?
				""";
		return jdbcTemplate.update(sql, id);
	}

	public int atualizarTarefa(Tarefa tarefa, Integer id) {
		String sql = """
				update tarefa 
				set descricao = ?, prioridade = ?, status = ? 
				WHERE id = ?
				""";
		return jdbcTemplate.update(sql, tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.getStatus(), id);
	}
}

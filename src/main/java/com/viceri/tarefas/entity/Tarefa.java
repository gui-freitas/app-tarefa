package com.viceri.tarefas.entity;

public class Tarefa {

	private Integer id;
	private String descricao;
	private Integer prioridade;
	private Integer status;
	
	public Tarefa () { }

	public Tarefa(Integer id, String descricao, Integer prioridade, Integer status) {
		this.id = id;
		this.descricao = descricao;
		this.prioridade = prioridade;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
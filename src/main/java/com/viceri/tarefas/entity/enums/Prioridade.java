package com.viceri.tarefas.entity.enums;

public enum Prioridade {
	ALTA(1, "Alta"),
	MEDIA(2, "Media"),
	BAIXA(3, "Baixa");
	
	private int codigo;
	private String descricao;
	
	private Prioridade(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Prioridade toEnum(String descricao) {
		if (descricao == null) {
			return null;
		}
		
		for (Prioridade prioridade : Prioridade.values()){
			if (descricao.equals(prioridade.getDescricao())) {
				return prioridade;
			}
		}
		throw new IllegalArgumentException("Prioridade invalida: " + descricao);
	}
};
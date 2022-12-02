package com.viceri.tarefas.entity.enums;

public enum Status {
	PENDENTE(1, "Pendente"),
	CONCLUIDA(2, "Concluida"),
	CANCELADA(3, "Cancelada");
	
	private int codigo;
	private String descricao;
	
	private Status(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Status toEnum(String descricao) {
		if (descricao == null) {
			return null;
		}
		
		for (Status status : Status.values()){
			if (descricao.equals(status.getDescricao())) {
				return status;
			}
		}
		throw new IllegalArgumentException("Status invalido: " + descricao);
	}
	
	public static Status toEnum(int codigo) {
		for (Status status : Status.values()){
			if (codigo == status.getCodigo()) {
				return status;
			}
		}
		throw new IllegalArgumentException("Status invalido: " + codigo);
	}
};
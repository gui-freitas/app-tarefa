package com.viceri.tarefas.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.viceri.tarefas.entity.Usuario;

@Repository
public class UsuarioRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int inserirUsuario(Usuario usuario) {
		String sql = """
				INSERT INTO usuario(nome, email, senha)
				VALUES (?, ?, ?)
				""";
		
		return jdbcTemplate.update(sql, usuario.getNome(), usuario.getEmail(), usuario.getSenha());
	}

	public boolean consultarPorEmail(String email) {
		String sql = """
				SELECT * 
				FROM usuario 
				WHERE UPPER(email) = UPPER(?)
				""";
		
		List<Usuario> lista =  jdbcTemplate.query(
				sql, (rs, rowNum) -> new Usuario(rs.getInt("id"), 
						rs.getString("nome"), 
						rs.getString("email"), 
						rs.getString("senha")),
				email);
		
		return !lista.isEmpty();
	}
}
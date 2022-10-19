package br.com.recode.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.recode.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	List<Usuario> findByNome(String nome);
}

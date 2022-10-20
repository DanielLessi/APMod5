package br.com.recode.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.recode.entidades.Destino;

public interface DestinoRepositorio extends JpaRepository<Destino, Long>{

	List<Destino> findByNome(String nome);
}

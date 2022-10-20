package br.com.recode.servico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.recode.entidades.Destino;
import br.com.recode.excecoes.BadRequestException;
import br.com.recode.repositorio.DestinoRepositorio;
import br.com.recode.requisicoes.DestinoCorpoRequisicaoPost;
import br.com.recode.requisicoes.DestinoCorpoRequisicaoPut;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DestinoServico{
	
	private final DestinoRepositorio destinoRepositorio;
	
	public List<Destino> listarTodosLista(){
		return destinoRepositorio.findAll();
	}
	
	public Page<Destino> listarTodosPaginado(Pageable pageable){
		return destinoRepositorio.findAll(pageable);
	}
	
	public List<Destino> buscarPorNome(String nome){
		return destinoRepositorio.findByNome(nome);
	}
	
	public Destino buscarPorIdOuEnviaMensagemErro(long id){
		return destinoRepositorio.findById(id)
				.orElseThrow(() -> new BadRequestException("Destino não encontrado"));
				
	}
	
	@Transactional
	public Destino cadastrarDestino(DestinoCorpoRequisicaoPost usuarioCorpoRequisicaoPost) {
		Destino destino = Destino.builder()
				.nome(usuarioCorpoRequisicaoPost.getNome())
				.pais(usuarioCorpoRequisicaoPost.getPais())
				.continente(usuarioCorpoRequisicaoPost.getContinente())
				.preco(usuarioCorpoRequisicaoPost.getPreco())
				.build();		
		return destinoRepositorio.save(destino);
	}
	
	public void deletarDestino(long id){
		destinoRepositorio.delete(buscarPorIdOuEnviaMensagemErro(id));
	}
	
	public void alterarDestino(DestinoCorpoRequisicaoPut usuarioCorpoRequisicaoPut){
		Destino destinoAtual = buscarPorIdOuEnviaMensagemErro(usuarioCorpoRequisicaoPut.getId());
		Destino destinoAlterado = Destino.builder()
				.id(usuarioCorpoRequisicaoPut.getId())
				.nome(usuarioCorpoRequisicaoPut.getNome())
				.pais(usuarioCorpoRequisicaoPut.getPais())
				.continente(usuarioCorpoRequisicaoPut.getContinente())
				.preco(usuarioCorpoRequisicaoPut.getPreco())
				.build();
		
		destinoAlterado.setId(destinoAtual.getId());
		destinoRepositorio.save(destinoAlterado);
	}
}

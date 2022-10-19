package br.com.recode.servico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.recode.entidades.Usuario;
import br.com.recode.excecoes.BadRequestException;
import br.com.recode.repositorio.UsuarioRepositorio;
import br.com.recode.requisicoes.UsuarioCorpoRequisicaoPost;
import br.com.recode.requisicoes.UsuarioCorpoRequisicaoPut;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServico{
	
	private final UsuarioRepositorio usuarioRepositorio;
	
	public List<Usuario> listarTodos(){
		return usuarioRepositorio.findAll();
	}
	
	public List<Usuario> buscarPorNome(String nome){
		return usuarioRepositorio.findByNome(nome);
	}
	
	public Usuario buscarPorIdOuEnviaMensagemErro(long id){
		return usuarioRepositorio.findById(id)
				.orElseThrow(() -> new BadRequestException("Usuário não encontrado"));
				
	}
	
	@Transactional
	public Usuario cadastrarUsuario(UsuarioCorpoRequisicaoPost usuarioCorpoRequisicaoPost) {
		Usuario usuario = Usuario.builder()
				.nome(usuarioCorpoRequisicaoPost.getNome())
				.sobrenome(usuarioCorpoRequisicaoPost.getSobrenome())
				.email(usuarioCorpoRequisicaoPost.getEmail())
				.senha(usuarioCorpoRequisicaoPost.getSenha())
				.build();		
		return usuarioRepositorio.save(usuario);
	}
	
	public void deletarUsuario(long id){
		usuarioRepositorio.delete(buscarPorIdOuEnviaMensagemErro(id));
	}
	
	public void alterarUsuario(UsuarioCorpoRequisicaoPut usuarioCorpoRequisicaoPut){
		Usuario usuarioAtual = buscarPorIdOuEnviaMensagemErro(usuarioCorpoRequisicaoPut.getId());
		Usuario usuarioAlterado = Usuario.builder()
				.id(usuarioCorpoRequisicaoPut.getId())
				.nome(usuarioCorpoRequisicaoPut.getNome())
				.sobrenome(usuarioCorpoRequisicaoPut.getSobrenome())
				.email(usuarioCorpoRequisicaoPut.getEmail())
				.senha(usuarioCorpoRequisicaoPut.getSenha())
				.build();
		
		usuarioAlterado.setId(usuarioAtual.getId());
		usuarioRepositorio.save(usuarioAlterado);
	}
}

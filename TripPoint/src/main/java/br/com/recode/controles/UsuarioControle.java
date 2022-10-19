package br.com.recode.controles;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import br.com.recode.entidades.Usuario;
import br.com.recode.requisicoes.UsuarioCorpoRequisicaoPost;
import br.com.recode.requisicoes.UsuarioCorpoRequisicaoPut;
import br.com.recode.servico.UsuarioServico;
import br.com.recode.utils.*;

@RestController
@RequestMapping("usuarios")
@Log4j2
@RequiredArgsConstructor
public class UsuarioControle {
	private final DateUtil dateUtil;
	private final UsuarioServico usuarioServico;
	

	@GetMapping
	public ResponseEntity<List<Usuario>> list(){
		log.info(dateUtil.formatarDataLocalParaBancoDeDados(LocalDateTime.now()));
		return ResponseEntity.ok(usuarioServico.listarTodos());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable long id){
		return ResponseEntity.ok(usuarioServico.buscarPorIdOuEnviaMensagemErro(id));
	}
	
	@GetMapping(path = "/find")
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam String nome){
		return ResponseEntity.ok(usuarioServico.buscarPorNome(nome));
	}
	
	@PostMapping
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Valid UsuarioCorpoRequisicaoPost usuarioCorpoRequisicaoPost){
		return new ResponseEntity<>(usuarioServico.cadastrarUsuario(usuarioCorpoRequisicaoPost),HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable long id){
		usuarioServico.deletarUsuario(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<Void> alterarUsuario(@RequestBody UsuarioCorpoRequisicaoPut usuarioCorpoRequisicaoPut){
		usuarioServico.alterarUsuario(usuarioCorpoRequisicaoPut);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

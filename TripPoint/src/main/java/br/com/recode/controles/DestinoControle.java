package br.com.recode.controles;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.recode.entidades.Destino;
import br.com.recode.requisicoes.DestinoCorpoRequisicaoPost;
import br.com.recode.requisicoes.DestinoCorpoRequisicaoPut;
import br.com.recode.servico.DestinoServico;
import br.com.recode.utils.*;

@RestController
@RequestMapping("destinos")
@Log4j2
@RequiredArgsConstructor
public class DestinoControle {
	private final DateUtil dateUtil;
	private final DestinoServico destinoServico;
	

	@GetMapping
	public ResponseEntity<List<Destino>> listaDestinos(){
		log.info(dateUtil.formatarDataLocalParaBancoDeDados(LocalDateTime.now()));
		return ResponseEntity.ok(destinoServico.listarTodosLista());
	}
	
	@GetMapping(path = "/paginas")
	public ResponseEntity<Page<Destino>> listaDestinosPaginada(Pageable pageable){
		log.info(dateUtil.formatarDataLocalParaBancoDeDados(LocalDateTime.now()));
		return ResponseEntity.ok(destinoServico.listarTodosPaginado(pageable));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Destino> buscarPorId(@PathVariable long id){
		return ResponseEntity.ok(destinoServico.buscarPorIdOuEnviaMensagemErro(id));
	}
	
	@GetMapping(path = "/find")
	public ResponseEntity<List<Destino>> buscarPorNome(@RequestParam String nome){
		return ResponseEntity.ok(destinoServico.buscarPorNome(nome));
	}
	
	@PostMapping
	public ResponseEntity<Destino> cadastrarDestino(@RequestBody @Valid DestinoCorpoRequisicaoPost destinoCorpoRequisicaoPost){
		return new ResponseEntity<>(destinoServico.cadastrarDestino(destinoCorpoRequisicaoPost),HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletarDestino(@PathVariable long id){
		destinoServico.deletarDestino(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<Void> alterarDestino(@RequestBody DestinoCorpoRequisicaoPut destinoCorpoRequisicaoPut){
		destinoServico.alterarDestino(destinoCorpoRequisicaoPut);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

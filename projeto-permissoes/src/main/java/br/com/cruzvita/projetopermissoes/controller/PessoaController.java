package br.com.cruzvita.projetopermissoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cruzvita.projetopermissoes.dto.PessoaDTO;
import br.com.cruzvita.projetopermissoes.model.Pessoa;
import br.com.cruzvita.projetopermissoes.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService service;
	
	@GetMapping("/listar")
	public ResponseEntity<List<PessoaDTO>> obterTodasPessoas(){
		return service.obterTodasPessoas();
	}
	@GetMapping("/listar/{nome}")
	public ResponseEntity<Pessoa> obterPessoaPeloNome(@PathVariable String nome){
		return service.buscarPessoaPeloNome(nome);
	}
	@PostMapping("/cadastrar")
	public ResponseEntity<String>cadastrarPessoa(@RequestBody PessoaDTO pessoa){
		return service.cadastrarPessoa(pessoa);
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<String>editarPessoa(@RequestBody PessoaDTO pessoa, @PathVariable Integer id ) throws Exception{
		return service.editarPessoaPassandoId(pessoa, id);
	}
	@DeleteMapping("/deletar{id}")
	public ResponseEntity<Integer>deletarPessoa(@PathVariable Integer id){
		return service.deletarPessoaPassandoId(id);
	}
}

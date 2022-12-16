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
	
	@GetMapping("/listarpornome")
	public ResponseEntity<Pessoa> obterPessoaPeloNome(String nome){
		return service.buscarPessoaPeloNome(nome);
	}
	@PostMapping("/cadastrarpessoa")
	public ResponseEntity<String>cadastrarPessoa(@RequestBody PessoaDTO pessoa){
		return service.cadastrarPessoa(pessoa);
	}
	@PutMapping("/atualizarpessoa/{id}")
	public ResponseEntity<String>editarPessoa(@RequestBody PessoaDTO pessoa, @PathVariable Long id ) throws Exception{
		return service.editarPessoaPassandoId(pessoa, id);
	}
	@DeleteMapping("/deletar")
	public ResponseEntity<Long>deletarPessoa(@PathVariable Long id){
		return service.deletarPessoaPassandoId(id);
	}
}

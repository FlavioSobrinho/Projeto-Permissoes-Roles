package br.com.cruzvita.projetopermissoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cruzvita.projetopermissoes.dto.ProdutoDTO;
import br.com.cruzvita.projetopermissoes.model.Produto;
import br.com.cruzvita.projetopermissoes.service.ProdutoService;
import lombok.Delegate;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> todosProdutos(){
		return produtoService.todosProdutos();
	}
	@GetMapping("/listar/{nome}")
	public ResponseEntity<Produto> buscaProdutoPeloId (@PathVariable String id){
		return produtoService.buscaPeloId(id);
	}
	@PostMapping("/cadastrar")
	public ResponseEntity<String> cadastrarProduto(@RequestBody ProdutoDTO produto){
		return produtoService.cadastraProduto(produto);
	}
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<String> editarPessoa(@RequestBody ProdutoDTO produto, @PathVariable Integer id)throws Exception {
		return produtoService.editarProdutoPassandoId(produto, id);
	}
	@Delegate("/deletar/{id}")
	public ResponseEntity<Integer> deletarProduto(@PathVariable Integer id){
		return produtoService.deletarProdutoPassandoId(id);
	}
	
}

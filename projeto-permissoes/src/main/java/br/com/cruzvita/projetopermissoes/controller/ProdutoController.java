package br.com.cruzvita.projetopermissoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cruzvita.projetopermissoes.dto.BuscaProdutoNomeDTO;
import br.com.cruzvita.projetopermissoes.dto.ProdutoDTO;
import br.com.cruzvita.projetopermissoes.model.Produto;
import br.com.cruzvita.projetopermissoes.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/todos/{idPessoa}")
	public ResponseEntity<List<ProdutoDTO>> todosProdutos(@PathVariable Integer idPessoa){
		return produtoService.todosProdutos(idPessoa);
	}
	@GetMapping("/listar/{idPessoa}")
	public ResponseEntity<Produto> buscaProdutoPeloNome (@RequestBody BuscaProdutoNomeDTO nomeProduto, @PathVariable Integer idPessoa){
		return produtoService.buscaProdutoPeloNome(nomeProduto.getNome(), idPessoa);
	}
	@PostMapping("/cadastrar/{idPessoa}")
	public ResponseEntity<String> cadastraProduto(@RequestBody ProdutoDTO produto, @PathVariable Integer idPessoa){
		return produtoService.cadastraProduto(produto, idPessoa);
	}
	@PutMapping("/atualizar/{id}/{idPessoa}")
	public ResponseEntity<String> editarProduto(@RequestBody ProdutoDTO produto, @PathVariable Integer id, @PathVariable Integer idPessoa)throws Exception {
		return produtoService.editarProduto(produto, id, idPessoa);
	}
	@DeleteMapping("/deleta/{id}/{idPessoa}")
	public ResponseEntity<Integer> deletarProduto(@PathVariable Integer id, @PathVariable Integer idPessoa){
		return produtoService.deletarProdutoPassandoId(id, idPessoa);
	}
	
}

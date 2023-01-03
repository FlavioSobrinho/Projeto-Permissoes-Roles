package br.com.cruzvita.projetopermissoes.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.cruzvita.projetopermissoes.autorization.AutorizacaoPessoa;
import br.com.cruzvita.projetopermissoes.dto.ProdutoDTO;
import br.com.cruzvita.projetopermissoes.enums.Operacoes;
import br.com.cruzvita.projetopermissoes.model.PessoaRole;
import br.com.cruzvita.projetopermissoes.model.Produto;
import br.com.cruzvita.projetopermissoes.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private AutorizacaoPessoa autorizaPessoa;

	public ResponseEntity<List<ProdutoDTO>> todosProdutos(Integer idPessoa,PessoaRole pessoaRole) {
		
		if(autorizaPessoa.autorizaPessoaBuscarTodos(idPessoa, Operacoes.BUSCA, pessoaRole)) {
			
			List<Produto> produtos = produtoRepository.findAll();
			List<ProdutoDTO> prodDTO = produtos
					.stream()
					.map(user -> modelMapper
							.map(user, ProdutoDTO.class))
					.collect(Collectors.toList());
			
			return new ResponseEntity<>(prodDTO, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	
	public ResponseEntity<Produto> buscaProdutoPeloNome(String nome, Integer idPessoa){
		
		if (autorizaPessoa.autorizaPessoaBuscarPorNome(idPessoa, Operacoes.BUSCA)) {
			return new ResponseEntity<>(produtoRepository.
					findByNome(nome), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	public ResponseEntity<String> editarProduto(Produto dto, Integer idPessoa) {
		if(autorizaPessoa.autorizaPessoaEditar(idPessoa, Operacoes.ATUALIZA)) {
			
			Produto produto = modelMapper.map(idPessoa, Produto.class);
			
			return new ResponseEntity<String>("Modificação de Produto Concluido", HttpStatus.ACCEPTED);
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Voce não está autorizado");
		}
		
	}
	
	public ResponseEntity<String> cadastraProduto(ProdutoDTO prodDTO, Integer idPessoa) {
		if(autorizaPessoa.autorizaPessoaCadastrar(idPessoa, Operacoes.CADASTRA)) {
			
			Produto produto = modelMapper.map(prodDTO, Produto.class);
			produtoRepository.save(produto);
			return new ResponseEntity<String>("Cadastro de Produto Concluido",HttpStatus.ACCEPTED);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Voce não está autorizado");
		}
		
	}
	
	public ResponseEntity<String> editarProduto(@RequestBody ProdutoDTO prodDTO, @PathVariable Integer id, Integer idPessoa) throws InstanceNotFoundException{
		
		if(autorizaPessoa.autorizaPessoaEditar(idPessoa, Operacoes.ATUALIZA)) {
			
			Produto produto = produtoRepository.findById(id).orElseThrow(() -> new InstanceNotFoundException());
			produto = modelMapper.map(prodDTO, Produto.class);
			produto.setId(id);
			produtoRepository.save(produto);
			
			return new ResponseEntity<String>("Modificação feita com sucesso", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Você não tem autorização para modificar produtos.",HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	public ResponseEntity<Integer> deletarProdutoPassandoId(Integer id, Integer idPessoa){
		
		if (autorizaPessoa.autorizaPessoaExcluir(idPessoa, Operacoes.DELETA)) {
			produtoRepository.deleteById(id);
			return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
}

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

import br.com.cruzvita.projetopermissoes.dto.ProdutoDTO;
import br.com.cruzvita.projetopermissoes.model.Produto;
import br.com.cruzvita.projetopermissoes.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<List<ProdutoDTO>> todosProdutos() {
		
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoDTO> prodDTO = produtos
				.stream()
				.map(user -> modelMapper
				.map(user, ProdutoDTO.class))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(prodDTO, HttpStatus.ACCEPTED);
	}
	
	
	public ResponseEntity<Produto> buscaProdutoPeloNome(String nome){
		return new ResponseEntity<>(produtoRepository.
				findByNome(nome), HttpStatus.ACCEPTED);
	}
	public Produto editarProduto(Produto dto, Integer id) {
		Produto produto = modelMapper.map(id, Produto.class);
		
		return produto;
	}
	
	public ResponseEntity<String> cadastraProduto(ProdutoDTO prodDTO) {
		Produto produto = modelMapper.map(prodDTO, Produto.class);
		produtoRepository.save(produto);
		return new ResponseEntity<String>("Cadastro de Produto Cncluido",HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<String> editarProduto(@RequestBody ProdutoDTO prodDTO, @PathVariable Integer id) throws InstanceNotFoundException{
		Produto produto = produtoRepository.findById(id).orElseThrow(() -> new InstanceNotFoundException());
		produto = modelMapper.map(prodDTO, Produto.class);
		produto.setId(id);
		produtoRepository.save(produto);
		
		return new ResponseEntity<>("Modificação feita com sucesso", HttpStatus.CREATED);
	}
	
	public ResponseEntity<Integer> deletarProdutoPassandoId(Integer id){
		produtoRepository.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
	}
	
}

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
import br.com.cruzvita.projetopermissoes.model.Produto;
import br.com.cruzvita.projetopermissoes.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AutorizacaoPessoa autorizacaoPessoa;

	@Autowired
	private AutorizacaoPessoa autorizaPessoa;

	public ResponseEntity<List<ProdutoDTO>> todosProdutos(Integer idPessoa) {
		try {
			if (autorizacaoPessoa.autorizaPessoaBuscarTodos(idPessoa, Operacoes.BUSCA)) {

				List<Produto> produtos = produtoRepository.findAll();
				List<ProdutoDTO> prodDTO = produtos.stream().map(user -> modelMapper.map(user, ProdutoDTO.class))
						.collect(Collectors.toList());

				return new ResponseEntity<>(prodDTO, HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<List<ProdutoDTO>>(HttpStatus.UNAUTHORIZED);	
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	public ResponseEntity<Produto> buscaProdutoPeloNome(String nomeProduto, Integer idPessoa) {
		try {
			if (autorizaPessoa.autorizaPessoaBuscarPorNome(idPessoa, Operacoes.BUSCA)) {
				Produto produto = produtoRepository.findByNome(nomeProduto).orElseThrow();
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(produto);

			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
	
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	public ResponseEntity<String> editarProduto(Produto dto, Integer idPessoa) {
		try {
			if (autorizaPessoa.autorizaPessoaEditar(idPessoa, Operacoes.ATUALIZA)) {

				Produto produto = modelMapper.map(idPessoa, Produto.class);

				return new ResponseEntity<String>("Modificação de Produto Concluido", HttpStatus.ACCEPTED);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Voce não tem autorização para executar a operação ou usuario não existe no banco de dados.");
			}

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<String> cadastraProduto(ProdutoDTO prodDTO, Integer idPessoa) {
		try {
			if (autorizaPessoa.autorizaPessoaCadastrar(idPessoa, Operacoes.CADASTRA)) {

				Produto produto = modelMapper.map(prodDTO, Produto.class);
				produtoRepository.save(produto);
				return new ResponseEntity<String>("Cadastro de Produto Concluido", HttpStatus.ACCEPTED);
			}else { 
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Voce não tem autorização para executar a operação ou usuario não existe no banco de dados.");
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> editarProduto(@RequestBody ProdutoDTO prodDTO, @PathVariable Integer id,
			Integer idPessoa) throws InstanceNotFoundException {
			
		try {
			if (autorizaPessoa.autorizaPessoaEditar(idPessoa, Operacoes.ATUALIZA)) {
				

				Produto produto = produtoRepository.findById(id).orElseThrow(() -> new InstanceNotFoundException());
				produto = modelMapper.map(prodDTO, Produto.class);
				produto.setId(id);
				produtoRepository.save(produto);

				return new ResponseEntity<String>("Modificação feita com sucesso", HttpStatus.OK); 
				}else {
					return new ResponseEntity<String>("Voce não tem autorização para executar a operação ou usuario não existe no banco de dados.",
							HttpStatus.UNAUTHORIZED);
				}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			
	}

	public ResponseEntity<Integer> deletarProdutoPassandoId(Integer id, Integer idPessoa) {
		if (autorizaPessoa.autorizaPessoaExcluir(idPessoa, Operacoes.DELETA)) {
			produtoRepository.deleteById(id);
			return new ResponseEntity<>(id, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

}

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.cruzvita.projetopermissoes.dto.PessoaDTO;
import br.com.cruzvita.projetopermissoes.model.Pessoa;
import br.com.cruzvita.projetopermissoes.model.StatusDoCadastro;
import br.com.cruzvita.projetopermissoes.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//RETORNA TODAS PESSOAS DA LISTA PESSOA
	public ResponseEntity<List<PessoaDTO>> obterTodasPessoas(){
		List<Pessoa> pessoas = repository.findAll(); 
		List<PessoaDTO> dto = pessoas.stream()
				.map(user -> modelMapper.map(user, PessoaDTO.class))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}
	
	//CADASTRA UMA NOVA PESSOA NA TABELA
	public ResponseEntity<String> cadastrarPessoa(PessoaDTO dto){
		Pessoa pessoa = modelMapper.map(dto, Pessoa.class);
		pessoa.setStatusDoCadastro(StatusDoCadastro.CADASTRO_ATIVO);
		repository.save(pessoa);
		
		return new ResponseEntity<String>("Cadastro Conluido.", HttpStatus.CREATED);
	}
	
	public ResponseEntity<Pessoa> buscarPessoaPeloNome(String nome){
		return new ResponseEntity<>(repository.
				findByNome(nome), HttpStatus.ACCEPTED);
	}
	
	//FAZER UMA ALTERAÇÃO EM UM USUARIO
	@PutMapping("/pessoa/{id}")
	public ResponseEntity<String> editarPessoaPassandoId(@RequestBody PessoaDTO dto, @PathVariable Long id) throws Exception{
	Pessoa pessoa = repository.findById(id).orElseThrow(() -> new InstanceNotFoundException());
	pessoa = modelMapper.map(dto, Pessoa.class);
	pessoa.setId(id);
	repository.save(pessoa);
	
	return new ResponseEntity<>("Modificação feita com sucesso", HttpStatus.CREATED);
	}
	
	//EXCLUSÃO DE USUARIO PASSANDO ID
	public ResponseEntity<Long> deletarPessoaPassandoId(Long id){
		repository.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
	}
}

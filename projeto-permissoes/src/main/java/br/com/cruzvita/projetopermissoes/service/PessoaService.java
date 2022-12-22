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

import br.com.cruzvita.projetopermissoes.dto.PessoaDTO;
import br.com.cruzvita.projetopermissoes.enums.StatusDoCadastro;
import br.com.cruzvita.projetopermissoes.model.Pessoa;
import br.com.cruzvita.projetopermissoes.repository.PessoaRepository;

@Service("userDetailsService")
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ModelMapper modelMapper;

		
	//RETORNA TODAS PESSOAS DA LISTA PESSOA
	public ResponseEntity<List<PessoaDTO>> obterTodasPessoas(){
		List<Pessoa> pessoas = pessoaRepository.findAll(); 
		List<PessoaDTO> dto = pessoas.stream()
				.map(user -> modelMapper.map(user, PessoaDTO.class))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}
	
	//CADASTRA UMA NOVA PESSOA NA TABELA
	public ResponseEntity<String> cadastrarPessoa(PessoaDTO dto){
		Pessoa pessoa = modelMapper.map(dto, Pessoa.class);
		pessoa.setStatusDoCadastro(StatusDoCadastro.CADASTRO_ATIVO);
		//pessoaRoleRepository.inserirPessoaRole(pessoa.getId());
		pessoaRepository.save(pessoa);
		
		return new ResponseEntity<String>("Cadastro Conluido.", HttpStatus.CREATED);
	}
	
	public ResponseEntity<Pessoa> buscarPessoaPeloNome(String nome){
		return new ResponseEntity<>(pessoaRepository.
				findByNome(nome), HttpStatus.ACCEPTED);
	}
	
	//FAZER UMA ALTERAÇÃO EM UM USUARIO
	public ResponseEntity<String> editarPessoaPassandoId(@RequestBody PessoaDTO dto, @PathVariable Integer id) throws Exception{
	Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new InstanceNotFoundException());
	pessoa = modelMapper.map(dto, Pessoa.class);
	pessoa.setId(id);
	pessoaRepository.save(pessoa);
	
	return new ResponseEntity<>("Modificação feita com sucesso", HttpStatus.CREATED);
	}
	
	//EXCLUSÃO DE USUARIO PASSANDO ID
	public ResponseEntity<Integer> deletarPessoaPassandoId(Integer id){
		pessoaRepository.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
	}

}

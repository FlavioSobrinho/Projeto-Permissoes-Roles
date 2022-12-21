package br.com.cruzvita.projetopermissoes.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.cruzvita.projetopermissoes.dto.PessoaDTO;
import br.com.cruzvita.projetopermissoes.model.Pessoa;
import br.com.cruzvita.projetopermissoes.model.Role;
import br.com.cruzvita.projetopermissoes.model.StatusDoCadastro;
import br.com.cruzvita.projetopermissoes.repository.PessoaRepository;
import br.com.cruzvita.projetopermissoes.repository.RoleRepository;

@Service("userDetailsService")
public class PessoaService implements UserDetailsService{

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override //METODO RECEBE O NOME DO USUARIO, SE EXISTIR O USUARIO CRIA UM USUARIO COM SPRING SECURITY E RETORNALO PARA UTILIZA-LO
	@Transactional //ANOTATION PARA RODAR O METODO EM APENAS UMA TRANSAÇÃO
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		Pessoa pessoa = pessoaRepository.findByNome(nome);
		Role role = roleRepository.findByIdPessoa(pessoa.getId());
		
		if (pessoa != null) {
			SimpleGrantedAuthority autoridade = new SimpleGrantedAuthority(role.getNome());
			Set<GrantedAuthority> autoridades = new HashSet<>();
			autoridades.add(autoridade);
			User user = new User(pessoa.getNome(), pessoa.getSenha(), autoridades);
			return user;
		}
		return null;
	}
	
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
		pessoaRepository.save(pessoa);
		
		return new ResponseEntity<String>("Cadastro Conluido.", HttpStatus.CREATED);
	}
	
	public ResponseEntity<Pessoa> buscarPessoaPeloNome(String nome){
		return new ResponseEntity<>(pessoaRepository.
				findByNome(nome), HttpStatus.ACCEPTED);
	}
	
	//FAZER UMA ALTERAÇÃO EM UM USUARIO
	@PutMapping("/pessoa/{id}")
	public ResponseEntity<String> editarPessoaPassandoId(@RequestBody PessoaDTO dto, @PathVariable Long id) throws Exception{
	Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new InstanceNotFoundException());
	pessoa = modelMapper.map(dto, Pessoa.class);
	pessoa.setId(id);
	pessoaRepository.save(pessoa);
	
	return new ResponseEntity<>("Modificação feita com sucesso", HttpStatus.CREATED);
	}
	
	//EXCLUSÃO DE USUARIO PASSANDO ID
	public ResponseEntity<Long> deletarPessoaPassandoId(Long id){
		pessoaRepository.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
	}

}

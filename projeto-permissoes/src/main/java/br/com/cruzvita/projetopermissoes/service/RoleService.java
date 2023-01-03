package br.com.cruzvita.projetopermissoes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cruzvita.projetopermissoes.dto.PessoaRolesDTO;
import br.com.cruzvita.projetopermissoes.dto.RoleDTO;
import br.com.cruzvita.projetopermissoes.model.Pessoa;
import br.com.cruzvita.projetopermissoes.model.PessoaRole;
import br.com.cruzvita.projetopermissoes.model.Role;
import br.com.cruzvita.projetopermissoes.repository.PessoaRoleRepository;
import br.com.cruzvita.projetopermissoes.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private PessoaRoleRepository pessoaRoleRepository;

	@Autowired
	private RoleRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<List<RoleDTO>> obterTodasRoles() {
		List<Role> roles = repository.findAll();
		List<RoleDTO> dto = roles.stream().map(user -> modelMapper.map(user, RoleDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}

	public Role buscaRolesId(Integer id) {
		return repository.findById(id).orElseThrow();
	}

	public RoleDTO cadastrarRole(RoleDTO dto) {
		Role role = modelMapper.map(dto, Role.class);
		repository.save(role);
		return dto;
	}

	public Role editarRole(RoleDTO dto, Integer id) {
		Role role = modelMapper.map(id, Role.class);

		return role;
	}

	public ResponseEntity<Integer> deletarPessoa(Integer id) {
		repository.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<String> cadastraRolePessoa(Pessoa pessoa) {
		PessoaRole pessoaRole = new PessoaRole();
		pessoaRole.setRoleId(2);
		pessoaRole.setPessoaId(pessoa.getId());
		pessoaRoleRepository.save(pessoaRole);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	public ResponseEntity<String> inclusaoRolePessoa(PessoaRolesDTO pessoaRoles, Integer id) {

		for (Integer roles : pessoaRoles.getRoles()) {
			PessoaRole pessoa = new PessoaRole(null, id, roles);
			pessoaRoleRepository.save(pessoa);

		}

		return ResponseEntity.status(HttpStatus.CREATED).body("Inclusão de Role foi Feita");
	}
	
	public ResponseEntity<Integer> deletarRolePessoa(Integer pessoaId, Integer roleId) {
		try {
			pessoaRoleRepository.deletarRolePessoa(pessoaId, roleId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(HttpStatus.ACCEPTED);
	}
	
}

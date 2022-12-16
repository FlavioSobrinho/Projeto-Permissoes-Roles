package br.com.cruzvita.projetopermissoes.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cruzvita.projetopermissoes.dto.RoleDTO;
import br.com.cruzvita.projetopermissoes.model.Role;
import br.com.cruzvita.projetopermissoes.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	
	  @Autowired private ModelMapper modelMapper;
	 
	
	public Role buscaRolesId(Long id) {
		return repository.findById(id).orElseThrow();
	}
	
	public RoleDTO cadastrarPessoa(RoleDTO dto) {
		Role role = modelMapper.map(dto, Role.class);
		repository.save(role);
		return dto;
	}
	
	public Role editarRole(RoleDTO dto, Long id) {
		Role role = modelMapper.map(id, Role.class);
		role.setId(dto.getId());
		
		return role;
	}
	
	public void deletarPessoa(Long id) {
		repository.deleteById(id);
	}
}

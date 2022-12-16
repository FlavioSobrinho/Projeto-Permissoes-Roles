package br.com.cruzvita.projetopermissoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cruzvita.projetopermissoes.model.Role;
import br.com.cruzvita.projetopermissoes.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	/*
	 * @Autowired private ModelMapper modelMapper;
	 */
	
	public Role buscaRoles(Long id) {
		Role role = repository.findById(id).orElseThrow();
		
		return role;
	}
}

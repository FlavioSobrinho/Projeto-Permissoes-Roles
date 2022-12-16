package br.com.cruzvita.projetopermissoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cruzvita.projetopermissoes.model.Role;
import br.com.cruzvita.projetopermissoes.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService service;
	
	@GetMapping("/{id}")
	public Role buscaRolePorId(@PathVariable Long id) {
		Role role = service.buscaRolesId(id);
		return role;
	}
	
}

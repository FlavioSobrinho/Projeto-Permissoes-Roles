package br.com.cruzvita.projetopermissoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cruzvita.projetopermissoes.dto.RoleDTO;
import br.com.cruzvita.projetopermissoes.model.Role;
import br.com.cruzvita.projetopermissoes.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService service;
	
	@GetMapping("/listarroles")
	public ResponseEntity<List<RoleDTO>>obterTodasRoles(){
		return service.obterTodasRoles();
	}
	
	@GetMapping("/{id}")
	public Role buscaRolePorId(@PathVariable Long id) {
		Role role = service.buscaRolesId(id);
		return role;
	}
	
	@PostMapping("/cadastrarpessoa")
	public RoleDTO cadastrarRole(@RequestBody RoleDTO dto){
		return service.cadastrarPessoa(dto);
	}
	
	@PutMapping("/atualizarpessoa/{id}")
	public Role editarRole(@RequestBody RoleDTO dto, @PathVariable Long id){
		return service.editarRole(dto, id);
		
	}
	
	@DeleteMapping("/deletarrole")
	public ResponseEntity<Long>deletarRole(@PathVariable Long id){
		return service.deletarPessoa(id);
	}
}

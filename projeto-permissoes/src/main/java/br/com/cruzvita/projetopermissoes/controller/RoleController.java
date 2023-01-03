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

import br.com.cruzvita.projetopermissoes.dto.PessoaRoleExclusaoDTO;
import br.com.cruzvita.projetopermissoes.dto.PessoaRolesDTO;
import br.com.cruzvita.projetopermissoes.dto.RoleDTO;
import br.com.cruzvita.projetopermissoes.model.Role;
import br.com.cruzvita.projetopermissoes.service.RoleService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService service;
	
	@GetMapping("/listar")
	public ResponseEntity<List<RoleDTO>>obterTodasRoles(){
		return service.obterTodasRoles();
	}
	
	@GetMapping("/buscar/{id}")
	public Role buscaRolePorId(@PathVariable Integer id) {
		Role role = service.buscaRolesId(id);
		return role;
	}
	
	@PostMapping("/cadastrar")
	public RoleDTO cadastrarRole(@RequestBody RoleDTO dto){
		return service.cadastrarRole(dto);
	}
	
	@PutMapping("/atualizar/{id}")
	public Role editarRole(@RequestBody RoleDTO dto, @PathVariable Integer id){
		return service.editarRole(dto, id);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Integer>deletarRole(@PathVariable Integer id){
		return service.deletarPessoa(id);
	}
	
	@PostMapping("/cadastrarrole/{id}")
	public ResponseEntity<String> cadastrarRolePessoa(@RequestBody PessoaRolesDTO roles, @PathVariable Integer id) {
		
		return service.inclusaoRolePessoa(roles, id);
	}
	
	@DeleteMapping("/deletar-role-pessoa/{id}")
	public ResponseEntity<Integer>deletarRolePessoa(@PathVariable Integer id, @RequestBody PessoaRoleExclusaoDTO role){
		return service.deletarRolePessoa(id, role.getRole());
	}
}

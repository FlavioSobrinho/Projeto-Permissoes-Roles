package br.com.cruzvita.projetopermissoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cruzvita.projetopermissoes.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findRoleByNome(String nome);
	
	Optional<Role> findRoleById(Integer id);
}

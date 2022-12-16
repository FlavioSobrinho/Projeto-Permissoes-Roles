package br.com.cruzvita.projetopermissoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cruzvita.projetopermissoes.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByNome(String nome);
	
	Optional<Role> findById(Long id);
}

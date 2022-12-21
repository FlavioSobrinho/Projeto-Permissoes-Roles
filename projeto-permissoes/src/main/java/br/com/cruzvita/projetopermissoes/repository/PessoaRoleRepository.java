package br.com.cruzvita.projetopermissoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cruzvita.projetopermissoes.model.PessoaRole;

@Repository
public interface PessoaRoleRepository extends JpaRepository<PessoaRole, Long> {
	Optional<PessoaRole> findById(Long id);
}

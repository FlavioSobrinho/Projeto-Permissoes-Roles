package br.com.cruzvita.projetopermissoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cruzvita.projetopermissoes.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	Pessoa findByNome(String nome);
	
	Optional<Pessoa> findById(Long id);
	
}

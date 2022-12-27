package br.com.cruzvita.projetopermissoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cruzvita.projetopermissoes.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	Produto findByNome(String nome);
}

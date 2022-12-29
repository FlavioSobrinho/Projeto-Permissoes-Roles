package br.com.cruzvita.projetopermissoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cruzvita.projetopermissoes.model.PessoaRole;

@Repository
public interface PessoaRoleRepository extends JpaRepository<PessoaRole, Integer>{

	@Transactional
	@Modifying //CONFIRMA O UPDATE
	@Query(value = "UPDATE pessoa_role SET role_id = NULL WHERE pessoa_id = :pessoaId AND role_id = :roleId", nativeQuery = true)
	Integer deletarRolePessoa (@Param(value = "roleId") Integer roleId ,@Param(value = "pessoaId") Integer pessoaId);
	
	@Query(value = "SELECT pessoas.nome, roles.nome FROM pessoa_role "
			+ "INNER JOIN pessoas ON pessoa_role.pessoa_id = :pessoas.id "
			+ "INNER JOIN roles ON pessoa_role.role_id = roles.id WHERE pessoas.id = 1", nativeQuery = true)
	List<String> buscaRolePessoa (@Param(value = "pessoaid") Integer pessoaId);
	
}

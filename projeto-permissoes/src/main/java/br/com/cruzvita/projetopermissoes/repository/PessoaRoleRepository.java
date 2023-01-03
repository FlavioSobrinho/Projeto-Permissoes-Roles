package br.com.cruzvita.projetopermissoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cruzvita.projetopermissoes.dto.PessoaRoleAutorizacaoDTO;
import br.com.cruzvita.projetopermissoes.dto.RolesDTO;
import br.com.cruzvita.projetopermissoes.model.PessoaRole;

@Repository
public interface PessoaRoleRepository extends JpaRepository<PessoaRole, Integer>{

	@Transactional
	@Modifying //CONFIRMA O UPDATE
	@Query(value = "UPDATE pessoa_role SET role_id = NULL WHERE pessoa_id = :pessoaId AND role_id = :roleId", nativeQuery = true)
	Integer deletarRolePessoa (@Param(value = "roleId") Integer roleId, @Param(value = "pessoaId") Integer pessoaId);
	
	@Query(value = "SELECT p.nome as nomePessoa, r.nome as nomeRole FROM pessoa_role pr"
			+ "INNER JOIN pessoas p ON pr.pessoa_id = p.id"
			+ "INNER JOIN roles r ON pr.role_id = r.id WHERE p.id = :pessoa_id", nativeQuery = true)
	List<RolesDTO> buscaRolePessoa (@Param(value = "pessoa_id") Integer pessoaId);
	
	@Query(value = "SELECT r.id as roleId FROM pessoa_role pr "
			+ "INNER JOIN pessoas p ON pr.pessoa_id = p.id "
			+ "INNER JOIN roles r ON pr.role_id = r.id WHERE p.id = :pessoa_id", nativeQuery = true)
	List<Integer> buscaIdPessoaIdRole(@Param(value = "pessoa_id")Integer pessoaId);
	
}

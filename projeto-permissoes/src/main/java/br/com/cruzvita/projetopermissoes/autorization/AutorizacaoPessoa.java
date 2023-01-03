package br.com.cruzvita.projetopermissoes.autorization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cruzvita.projetopermissoes.enums.Operacoes;
import br.com.cruzvita.projetopermissoes.repository.PessoaRoleRepository;

@Component
public class AutorizacaoPessoa {

	@Autowired
	private PessoaRoleRepository pessoaRoleRepository;
	
	//MÉTODOS PARA AUTORIZAR PESSOA PARA MANIPULAR PRODUTOS.
	
	public Boolean autorizaPessoaBuscarTodos(Integer idPessoa, Operacoes operacoes){
		
		List<Integer> pessoaRoles = pessoaRoleRepository.buscaIdPessoaIdRole(idPessoa);
		
		if(pessoaRoles.toString().contains("1")||pessoaRoles.toString().contains("4")) {
			return true;
		}else {
			return false;
		}
	}
	public Boolean autorizaPessoaBuscarPorNome(Integer idPessoa, Operacoes operacoes){
		
		List<Integer> pessoaRoles = pessoaRoleRepository.buscaIdPessoaIdRole(idPessoa);
		
		if(pessoaRoles.toString().contains("1")||pessoaRoles.toString().contains("4")) {
			
			return true;
		}else {
			return false;
		}
		
	}
	
	public Boolean autorizaPessoaCadastrar(Integer idPessoa, Operacoes operacoes){
		List<Integer> pessoaRoles = pessoaRoleRepository.buscaIdPessoaIdRole(idPessoa);
		
		if (pessoaRoles.toString().contains("1")||pessoaRoles.toString().contains("3")) {
			return true;
		}else {
		
		return false;
		}
	}
	
	public Boolean autorizaPessoaEditar(Integer idPessoa, Operacoes operacoes){
		List<Integer> pessoaRoles = pessoaRoleRepository.buscaIdPessoaIdRole(idPessoa);
		
		if (pessoaRoles.toString().contains("1")||pessoaRoles.toString().contains("5")) {
			return true;
		}else {
			return false;
		}
	}
	
	public Boolean autorizaPessoaExcluir(Integer idPessoa, Operacoes operacoes){
		List<Integer> pessoaRoles = pessoaRoleRepository.buscaIdPessoaIdRole(idPessoa);
		
		if (pessoaRoles.toString().contains("1")||pessoaRoles.toString().contains("6")) {
			return true;
		}else {
			return false;
		}
	}
}
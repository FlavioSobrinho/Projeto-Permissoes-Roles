package br.com.cruzvita.projetopermissoes.autorization;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cruzvita.projetopermissoes.enums.Operacoes;
import br.com.cruzvita.projetopermissoes.model.PessoaRole;
import br.com.cruzvita.projetopermissoes.repository.PessoaRoleRepository;
import br.com.cruzvita.projetopermissoes.service.ProdutoService;

public class AutorizacaoPessoa {

	@Autowired
	private PessoaRoleRepository pessoaRoleRepository;
	
	private Operacoes operacoes;
	
	private ProdutoService service;
	
	//MÉTODOS PARA AUTORIZAR PESSOA PARA MANIPULAR PRODUTOS.
	
	public Boolean autorizaPessoaBuscarTodos(Integer idPessoa, Operacoes operacoes, PessoaRole pessoaRole){
		if(pessoaRole.getRoleId().equals(1)) {
			service.todosProdutos(idPessoa,pessoaRole);
			return true;
		}else {
			return false;
		}
	}
	public Boolean autorizaPessoaBuscarPorNome(Integer idPessoa, Operacoes operacoes){
		
		return true;
	}
	
	public Boolean autorizaPessoaCadastrar(Integer idPessoa, Operacoes operacoes){
		
		return true;
	}
	
	public Boolean autorizaPessoaEditar(Integer idPessoa, Operacoes operacoes){
		
		return true;
	}
	
	public Boolean autorizaPessoaExcluir(Integer idPessoa, Operacoes operacoes){
		
		return true;
	}
}
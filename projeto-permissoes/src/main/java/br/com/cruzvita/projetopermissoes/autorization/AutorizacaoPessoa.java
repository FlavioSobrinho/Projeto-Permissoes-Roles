package br.com.cruzvita.projetopermissoes.autorization;

import java.util.List;

import br.com.cruzvita.projetopermissoes.repository.PessoaRoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorizacaoPessoa {

	private PessoaRoleRepository pessoaRoleRepository;
	
	private Boolean autorizaPessoas(Integer idPessoa, String operacao){
		
		try {
			
			List<String> roles =  pessoaRoleRepository.buscaRolePessoa(idPessoa) ;
			
			if (operacao.equals("CRIACAO")) {
				if (roles.contains("ROLE_CRIADOR")){
					return true;
				} else {
					return false;
				}
			}else {
				return false;
			}
			
		} catch (Exception e) {
			
		}
		
		return true;
	}
}

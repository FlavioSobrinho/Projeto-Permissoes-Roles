package br.com.cruzvita.projetopermissoes.dto;

import br.com.cruzvita.projetopermissoes.model.StatusDoCadastro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

	private String id;
	private String nome;
	private StatusDoCadastro statusDoCadastro;
	
}

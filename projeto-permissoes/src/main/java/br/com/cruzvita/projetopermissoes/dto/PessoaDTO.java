package br.com.cruzvita.projetopermissoes.dto;

import br.com.cruzvita.projetopermissoes.enums.StatusDoCadastro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

	private String nome;
	private String cargo;
	private StatusDoCadastro statusDoCadastro;
	
}

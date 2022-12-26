package br.com.cruzvita.projetopermissoes.dto;

import java.util.List;

import br.com.cruzvita.projetopermissoes.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {
	
	private String nome;
	
	private List<Role> roles;
	
}

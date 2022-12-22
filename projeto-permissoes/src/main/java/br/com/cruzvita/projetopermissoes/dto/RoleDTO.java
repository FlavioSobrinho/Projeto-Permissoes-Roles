package br.com.cruzvita.projetopermissoes.dto;

import java.util.List;

import br.com.cruzvita.projetopermissoes.model.Pessoa;
import lombok.Data;

@Data
public class RoleDTO {
	
	private Integer id;
	private String nome;
	private String descricao;
	private List<Pessoa> pessoas;
}

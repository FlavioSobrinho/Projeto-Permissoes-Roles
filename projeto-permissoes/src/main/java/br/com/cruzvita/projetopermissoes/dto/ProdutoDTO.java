package br.com.cruzvita.projetopermissoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

	private String nome;
	
	private Integer preco;
	
	private String descricao;
}

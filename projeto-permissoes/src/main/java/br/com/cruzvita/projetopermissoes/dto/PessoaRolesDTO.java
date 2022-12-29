package br.com.cruzvita.projetopermissoes.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRolesDTO {

	List<Integer> roles;
}

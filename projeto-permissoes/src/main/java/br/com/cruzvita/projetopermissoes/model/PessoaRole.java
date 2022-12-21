package br.com.cruzvita.projetopermissoes.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PessoasRoles")
public class PessoaRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
}

package br.com.cruzvita.projetopermissoes.model;


import java.util.List;

import br.com.cruzvita.projetopermissoes.enums.StatusDoCadastro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pessoas")
public class Pessoa {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "nome")
	private String nome;
	
	@Column (name = "status_do_cadastro")
	private StatusDoCadastro statusDoCadastro;
	
	@ManyToMany
	@JoinTable(name = "pessoa_role",
			joinColumns = @JoinColumn(name= "pessoa_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	
}

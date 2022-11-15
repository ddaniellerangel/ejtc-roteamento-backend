package br.com.ejtc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor
@EqualsAndHashCode(of = {"codCliente"})
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codCliente;

	@NotBlank(message = "O nome do Cliente deve ser preenchido")
	private String nome;

	@NotBlank(message = "O CPF/CNPJ do cliente deve ser preenchido")
	private String cpf_cnpj;

//	private List<Endereco> endereco;

}

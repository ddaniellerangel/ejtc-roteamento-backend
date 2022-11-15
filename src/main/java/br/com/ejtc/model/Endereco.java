package br.com.ejtc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor
@EqualsAndHashCode(of = {"codEndereco"})
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codEndereco;

	@NotBlank(message = "O CEP deve ser informado")
	private String cep;

	@NotBlank(message = "O nome da rua precisa ser informado")
	private String rua;

	@NotBlank(message = "O bairro deve ser preenchido")
	private String bairro;

	private Integer numero;
	
	private Boolean semNumero;

	@ManyToOne
    @JoinColumn(name = "codCidade")
	@NotBlank(message = "A cidade deve ser informada")
	private Cidade cidade;
	
	@ManyToOne
    @JoinColumn(name = "codCliente")
	@NotBlank(message = "O cliente deve ser informado")
	private Cliente cliente;

}

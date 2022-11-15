package br.com.ejtc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor
@EqualsAndHashCode(of = {"codEstado"})
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codEstado;

	@NotBlank(message = "Nome do Estado deve ser preenchido")
	private String nome;

	@Column(length = 2)
	@NotBlank(message = "Sigla do Estado deve ser preenchida")
	@Size(min = 2, max = 2, message = "Sigla do Estado deve ter 2 letras")
	private String sigla;

}

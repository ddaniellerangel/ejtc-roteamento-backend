package br.com.ejtc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor
@EqualsAndHashCode(of = {"codEntrega"})
public class Entrega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codEntrega;

	private Boolean feita;

	@NotNull(message = "Latitude precisa ser preenchida")
	private Double latitude;

	@NotNull(message = "Longitude precisa ser preenchida")
	private Double longitude;

	@ManyToOne
    @JoinColumn(name = "codRota")
	private Rota rota;
	
	@ManyToOne
    @JoinColumn(name = "codEndereco")
	private Endereco endereco;

}

package org.ntt.api.cuenta.bancaria.cliente.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * <b> Clase persona que sera heradad por cliente. </b>
 * 
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Pucha $, $Date: 19 abr. 2024 $]
 *          </p>
 */
@MappedSuperclass
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String direccion;

	private int edad;

	private String genero;

	@Column(unique = true)
	@NotBlank
	private String identificacion;

	@NotBlank
	private String nombre;

	@NotBlank
	private String telefono;

}
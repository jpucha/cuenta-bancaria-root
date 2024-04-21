/**
 * 
 */
package org.ntt.api.cuenta.bancaria.cliente.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * <b> Clase DTO para la entrada de los datos del cliente. </b>
 * 
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Pucha $, $Date: 19 abr. 2024 $]
 *          </p>
 */
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntradaDto {

	private String identificacion;

	private String contrasena;

	private String estado;

	private String direccion;

	private int edad;

	private String genero;

	private String nombre;

	private String telefono;

	private Long idCliente;

}

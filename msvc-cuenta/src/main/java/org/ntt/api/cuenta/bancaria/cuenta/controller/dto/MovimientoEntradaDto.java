/**
 * 
 */
package org.ntt.api.cuenta.bancaria.cuenta.controller.dto;

import lombok.Data;

/**
 * 
 * <b> Clase Dto para la entrada del movimiento. </b>
 * 
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Pucha $, $Date: 20 abr. 2024 $]
 *          </p>
 */
@Data
public class MovimientoEntradaDto {

	private Long idMovimiento;
	private double saldo;
	private String tipoMovimiento;
	private String fecha;
	private Long idCliente;
	private Long idCuenta;
	private double valor;
	private String identificacion;
	private int numeroCuenta;

}

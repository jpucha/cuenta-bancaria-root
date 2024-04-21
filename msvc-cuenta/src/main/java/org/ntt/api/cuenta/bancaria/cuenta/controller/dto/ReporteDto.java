package org.ntt.api.cuenta.bancaria.cuenta.controller.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <b> Interfaz para obtener los datos del reporte entre fechas. </b>
 * 
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Pucha $, $Date: 20 abr. 2024 $]
 *          </p>
 */
public interface ReporteDto {

	public Date getFecha();

	public String getNombre();

	public int getNumero();

	public String getTipoCuenta();

	public BigDecimal getSaldoAnterior();

	public String getEstado();

	public BigDecimal getValor();

	public BigDecimal getSaldo();

}

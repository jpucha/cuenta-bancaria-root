/**
 *
 */
package org.ntt.api.cuenta.bancaria.cuenta.service;

import java.util.List;
import org.ntt.api.cuenta.bancaria.cuenta.controller.dto.MovimientoEntradaDto;
import org.ntt.api.cuenta.bancaria.cuenta.exception.CuentaException;
import org.ntt.api.cuenta.bancaria.cuenta.model.entity.Movimiento;

/**
 *
 * <b> Interfaz del servicio para el Movimiento. </b>
 *
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Pucha $, $Date: 20 abr. 2024 $]
 *          </p>
 */
public interface MovimientoService {

    Movimiento create(MovimientoEntradaDto movimientoEntradaDto) throws CuentaException;

	List<Movimiento> obtenerPorNumeroCuentaPorFecha(int numeroCuenta, String fechaInicio,
		String fechaFin)
			throws CuentaException;

	/*Movimiento update(Movimiento movimiento);

	void delete(Long id);

	Optional<Movimiento> obtenerPorId(Long id);

	List<Movimiento> obtenerPorClienteCuenta(Long idCliente, Long idCuenta);

	List<Movimiento> obtenerPorIdentificacionNumeroCuenta(String identificacion, int numeroCuenta);

	Double obtenerSumaValorClienteCuentaFecha(Long clienteId, Long idCuenta, String tipoMovimiento, Date fecha);

	List<ReporteDto> obtenerPorFechas(Date fechaInicial, Date fechaFinal);*/

}

/**
 *
 */
package org.ntt.api.cuenta.bancaria.cuenta.service.impl;

import java.util.List;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import org.ntt.api.cuenta.bancaria.cuenta.controller.dto.MovimientoEntradaDto;
import org.ntt.api.cuenta.bancaria.cuenta.enumeration.TipoMovimientoEnum;
import org.ntt.api.cuenta.bancaria.cuenta.exception.CuentaException;
import org.ntt.api.cuenta.bancaria.cuenta.model.entity.Cuenta;
import org.ntt.api.cuenta.bancaria.cuenta.model.entity.Movimiento;
import org.ntt.api.cuenta.bancaria.cuenta.repository.MovimientoRepository;
import org.ntt.api.cuenta.bancaria.cuenta.service.CuentaService;
import org.ntt.api.cuenta.bancaria.cuenta.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <b> Servicio para el Movimiento. </b>
 *
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Jenny Pucha $, $Date: 20 abr. 2024 $]
 *     </p>
 */
@Service
public class MovimientoServiceImpl implements MovimientoService {

    private static final int limiteDiario = 1000;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaService cuentaService;

    @Override
    public Movimiento create(MovimientoEntradaDto movimientoEntradaDto) throws CuentaException {
        try {
            /*if (ObjectUtils.isEmpty(movimientoEntradaDto.getIdentificacion())
                || movimientoEntradaDto.getNumeroCuenta() == 0) {
                throw new CuentaException(
                    "La identificación y el número de cuenta son obligatorios");
            }*/
            /*Optional<ClienteModel> cliente = cuentaService.obtenerClientePorIdentificacion(
                movimientoEntradaDto.getIdentificacion());
            if (!cliente.isPresent()) {
                throw new CuentaException("El cliente no existe con la identificación ingresada.");
            }*/
            Optional<Cuenta> cuenta = cuentaService.obtenerPorNumeroCuenta(
                movimientoEntradaDto.getNumeroCuenta());
            if (!cuenta.isPresent()) {
                throw new CuentaException("La cuenta no existe con el número de cuenta ingresado.");
            }

            BigDecimal saldo = new BigDecimal(0);
            if (TipoMovimientoEnum.CREDITO.getDescripcion()
                .equalsIgnoreCase(movimientoEntradaDto.getTipoMovimiento())) {
                saldo = credito(cuenta.get().getSaldoInicial(),
                    BigDecimal.valueOf(movimientoEntradaDto.getValor()));
            } else if (TipoMovimientoEnum.DEBITO.getDescripcion()
                .equalsIgnoreCase(movimientoEntradaDto.getTipoMovimiento())) {
                saldo = debito(cuenta.get().getSaldoInicial(),
                    BigDecimal.valueOf(movimientoEntradaDto.getValor()));

            }
            Movimiento movimiento = Movimiento.builder().cuenta(cuenta.get())
                .idCuenta(cuenta.get().getIdCuenta()).idCuenta(cuenta.get().getIdCuenta())
                .valor(BigDecimal.valueOf(Math.abs(movimientoEntradaDto.getValor())))
                .saldo(saldo).saldoAnterior(cuenta.get().getSaldoInicial())
                .tipoMovimiento(movimientoEntradaDto.getTipoMovimiento()).fecha(new Date())
                .build();
            //Actualizamos el saldo en al cuenta.
            cuenta.get().setSaldoInicial(movimiento.getSaldo());
            cuentaService.update(cuenta.get());
            return movimientoRepository.save(movimiento);
        } catch (CuentaException e) {
            throw new CuentaException(e);
        }
    }

    /**
     * Metodo que resta al saldo inicial el monto dado.
     *
     * @param saldoInicial
     * @param monto
     * @return
     * @throws CuentaException
     */
    private BigDecimal debito(BigDecimal saldoInicial, BigDecimal monto) throws CuentaException {
        BigDecimal nuevoSaldo = saldoInicial.subtract(monto);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new CuentaException("Dinero insuficiente, para realizar el débito");
        }
        return saldoInicial = nuevoSaldo;
    }

    /**
     * Metodo que agrega al saldo el monto dado.
     *
     * @param saldoInicial
     * @param monto
     * @return
     */
    private BigDecimal credito(BigDecimal saldoInicial, BigDecimal monto) {
        return saldoInicial = saldoInicial.add(monto);
    }

    /**
     * Se obtiene todos los metodos
     * @return
     */
    @Override
    public List<Movimiento> obtenerPorNumeroCuentaPorFecha(int numeroCuenta, String fechaInicio,
        String fechaFin)
        throws CuentaException {
        Optional<Cuenta> cuenta = cuentaService.obtenerPorNumeroCuenta(
            numeroCuenta);
        if (!cuenta.isPresent()) {
            throw new CuentaException("La cuenta no existe con el número de cuenta ingresado.");
        }
        return movimientoRepository.findByIdCuenta(cuenta.get().getIdCuenta());
    }

	/*@Override
	public Movimiento update(Movimiento movimiento) {
		return movimientoRepository.save(movimiento);
	}

	@Override
	public void delete(Long id) {
		movimientoRepository.deleteById(id);

	}

	@Override
	public Optional<Movimiento> obtenerPorId(Long id) {

		return movimientoRepository.findById(id);
	}

	@Override
	public List<Movimiento> obtenerPorClienteCuenta(Long idCliente, Long idCuenta) {
		return movimientoRepository.findByIdClienteAndIdCuenta(idCliente, idCuenta);
	}

	@Override
	public List<Movimiento> obtenerPorIdentificacionNumeroCuenta(String identificacion, int numeroCuenta) {
		return movimientoRepository.buscarPorClienteCuenta(identificacion, numeroCuenta);
	}

	@Override
	public Double obtenerSumaValorClienteCuentaFecha(Long clienteId, Long idCuenta, String tipoMovimiento, Date fecha) {
		return movimientoRepository.sumaValorPorClienteCuentaFecha(clienteId, idCuenta, tipoMovimiento, fecha);
	}

	@Override
	public List<ReporteDto> obtenerPorFechas(Date fechaInicial, Date fechaFinal) {
		return movimientoRepository.buscarPorEntreFechas(fechaInicial, fechaFinal);
	}*/

}

/**
 *
 */
package org.ntt.api.cuenta.bancaria.cuenta.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.ntt.api.cuenta.bancaria.cuenta.controller.dto.MovimientoEntradaDto;
import org.ntt.api.cuenta.bancaria.cuenta.enumeration.TipoMovimientoEnum;
import org.ntt.api.cuenta.bancaria.cuenta.exception.CuentaException;
import org.ntt.api.cuenta.bancaria.cuenta.model.ClienteModel;
import org.ntt.api.cuenta.bancaria.cuenta.model.entity.Cuenta;
import org.ntt.api.cuenta.bancaria.cuenta.model.entity.Movimiento;
import org.ntt.api.cuenta.bancaria.cuenta.service.CuentaService;
import org.ntt.api.cuenta.bancaria.cuenta.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * <b> Clase controlador para los movimientos contables. </b>
 *
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Pucha $, $Date: 21 abr. 2022 $]
 *          </p>
 */
@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private static final Logger log = LoggerFactory.getLogger(MovimientoController.class);

    @Autowired
    private MovimientoService service;

    @Autowired
    private CuentaService cuentaService;

    /**
     *
     * <b> Metodo que crea los movimientos. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 20 abr. 2024]
     * </p>
     *
     * @param movimientoEntradaDto
     *            parametro de entrada
     * @return ResponseEntity<?> lista o mensaje de error
     */
    @PostMapping
    public ResponseEntity<?> create(
        @Validated @RequestBody MovimientoEntradaDto movimientoEntradaDto)  {
        try {
            return new ResponseEntity<Movimiento>(service.create(movimientoEntradaDto), HttpStatus.CREATED);
        } catch (CuentaException e) {
            log.error("Por favor comuniquese con el administrador", e);
            return new ResponseEntity<>(e.getCause().getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * <b> Metodo que obtiene todos los movimientos existentes. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 20 abr. 2024]
     * </p>
     *
     *
     * @return ResponseEntity<?> lista o mensaje de error
     **/
    @GetMapping(path = "/{numeroCuenta}")
    public ResponseEntity<?> obtenerPorNumeroCuentaPorFecha(@PathVariable int numeroCuenta, String fechaInicio,
        String fechaFin) throws CuentaException {

        if (numeroCuenta == 0) {
            return new ResponseEntity<>("Numero de cuenta no puede ser vacio.",
                HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Movimiento>>(service.obtenerPorNumeroCuentaPorFecha(numeroCuenta, fechaInicio,
            fechaFin), HttpStatus.CREATED);
    }

    /**
     *
     * <b> Metodo para obtiene los movimientos del cliente por su cuenta. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 20 abr. 2024]
     * </p>
     *
     * @param movimientoEntradaDto
     *            parametro de entrada
     * @return ResponseEntity<?> lista o mensaje de error
     **
     @GetMapping
     public ResponseEntity<?> obtenerMoviemientosPorNumeroCuenta(
     @RequestBody MovimientoEntradaDto movimientoEntradaDto) {
     try {

     if (ObjectUtils.isEmpty(movimientoEntradaDto.getIdentificacion())
     || ObjectUtils.isEmpty(movimientoEntradaDto.getNumeroCuenta())) {
     return new ResponseEntity<>("La identificación y el número de cuenta son obligatorios",
     HttpStatus.BAD_REQUEST);
     }
     Optional<Cliente> cliente = clienteService
     .obtenerPorIdentificacion(movimientoEntradaDto.getIdentificacion());
     if (ObjectUtils.isEmpty(cliente)) {
     return new ResponseEntity<>("El cliente no existe con la identificación", HttpStatus.BAD_REQUEST);
     }
     Optional<Cuenta> cuenta = cuentaService.obtenerPorNumeroCuenta(movimientoEntradaDto.getNumeroCuenta());
     if (ObjectUtils.isEmpty(cuenta)) {
     return new ResponseEntity<>("La cuenta no existe con el número de cuenta", HttpStatus.BAD_REQUEST);
     }

     List<Movimiento> listadoMovimiento = service.obtenerPorClienteCuenta(cliente.get().getClienteId(),
     cuenta.get().getIdCuenta());
     if (null == listadoMovimiento || listadoMovimiento.isEmpty()) {
     return new ResponseEntity<>("No existen movimientos con lo parametros indicados.", HttpStatus.OK);
     } else {
     return new ResponseEntity<List<Movimiento>>(listadoMovimiento, HttpStatus.OK);
     }

     } catch (Exception e) {
     log.error("Por favor comuniquese con el administrador", e);
     return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
     }
     }*/

     /*
      *
      * <b> Metodo que actualiza el moviemto. </b>
      * <p>
      * [Author: Jenny Pucha, Date: 20 abr. 2024]
      * </p>
      *
      * @param movimientoEntradaDto
     *            parametro de entrada
     * @return ResponseEntity<?> lista o mensaje de error
     *
     @PutMapping public ResponseEntity<?> update(@Validated @RequestBody MovimientoEntradaDto movimientoEntradaDto) {
     try {
     if (ObjectUtils.isEmpty(movimientoEntradaDto.getIdMovimiento())) {
     return new ResponseEntity<>("El id del movimiento es obligatorio", HttpStatus.BAD_REQUEST);
     }
     Optional<Movimiento> movimientoEncontrado = service.obtenerPorId(movimientoEntradaDto.getIdMovimiento());
     if (movimientoEncontrado.isPresent()) {
     Movimiento movimiento = movimientoEncontrado.get();
     SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

     Date date = formatter.parse(movimientoEntradaDto.getFecha());
     movimiento.setFecha(date);
     Movimiento movimientoGuardado = service.update(movimiento);
     return new ResponseEntity<Movimiento>(movimientoGuardado, HttpStatus.OK);
     }
     return new ResponseEntity<>("No se encuentra en movimiento", HttpStatus.BAD_REQUEST);

     } catch (Exception e) {
     log.error("Por favor comuniquese con el administrador", e);
     return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
     }
     }

     /**
      *
      * <b> Metodo que elimina un registro por su id. </b>
      * <p>
      * [Author: Jenny Pucha, Date: 20 abr. 2024]
      * </p>
      *
      * @param id
     *            parametro de entrada
     * @return ResponseEntity<?> lista o mensaje de error
     *
     @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable("id") Long id) {
     try {

     if (ObjectUtils.isEmpty(id)) {
     return new ResponseEntity<>("El id del movimiento es obligatorio", HttpStatus.BAD_REQUEST);
     }
     Optional<Movimiento> movimientoEncontrado = service.obtenerPorId(id);
     if (movimientoEncontrado.isPresent()) {
     service.delete(id);
     return new ResponseEntity<>("Registro Eliminado", HttpStatus.OK);
     }
     return new ResponseEntity<String>(
     "No se puede eliminar el movimiento con id: " + id + " no existe el registro",
     HttpStatus.BAD_REQUEST);

     } catch (Exception e) {
     log.error("Por favor comuniquese con el administrador", e);
     return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
     }
     }*/

}

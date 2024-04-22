/**
 *
 */
package org.ntt.api.cuenta.bancaria.cuenta.controller;

import java.util.List;
import org.ntt.api.cuenta.bancaria.cuenta.controller.dto.MovimientoEntradaDto;
import org.ntt.api.cuenta.bancaria.cuenta.controller.dto.ReporteDto;
import org.ntt.api.cuenta.bancaria.cuenta.exception.CuentaException;
import org.ntt.api.cuenta.bancaria.cuenta.model.entity.Movimiento;
import org.ntt.api.cuenta.bancaria.cuenta.service.CuentaService;
import org.ntt.api.cuenta.bancaria.cuenta.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b> Clase controlador para los movimientos contables. </b>
 *
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Jenny Pucha $, $Date: 21 abr. 2022 $]
 *     </p>
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
     * <b> Metodo que crea los movimientos. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 20 abr. 2024]
     * </p>
     *
     * @param movimientoEntradaDto parametro de entrada
     * @return ResponseEntity<?> lista o mensaje de error
     */
    @PostMapping
    public ResponseEntity<?> create(
        @Validated @RequestBody MovimientoEntradaDto movimientoEntradaDto) {
        try {
            return new ResponseEntity<Movimiento>(service.create(movimientoEntradaDto),
                HttpStatus.CREATED);
        } catch (CuentaException e) {
            log.error("Por favor comuniquese con el administrador", e);
            return new ResponseEntity<>(e.getCause().getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <b> Metodo que obtiene todos los movimientos existentes. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 20 abr. 2024]
     * </p>
     *
     * @return ResponseEntity<?> lista o mensaje de error
     **/
    @GetMapping(path = "/{numeroCuenta}")
    public ResponseEntity<?> obtenerPorNumeroCuentaPorFecha(@PathVariable int numeroCuenta)
        throws CuentaException {

        return new ResponseEntity<List<Movimiento>>(service.obtenerPorNumeroCuenta(numeroCuenta),
            HttpStatus.OK);
    }

    /**
     * <b> Metodo que actualiza el movimiento. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 20 abr. 2024]
     * </p>
     *
     * @param movimientoEntradaDto parametro de entrada
     * @return ResponseEntity<?> lista o mensaje de error
     */
    @PutMapping
    public ResponseEntity<?> update(
        @Validated @RequestBody MovimientoEntradaDto movimientoEntradaDto)
        throws CuentaException {
        return new ResponseEntity<Movimiento>(service.update(movimientoEntradaDto),
            HttpStatus.CREATED);
    }

    /**
     * <b> Metodo que elimina un registro por su id. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 20 abr. 2024]
     * </p>
     *
     * @param id parametro de entrada
     * @return ResponseEntity<?> lista o mensaje de error
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {

            service.deleteByIdMovimiento(id);
            return new ResponseEntity<>("Registro Eliminado", HttpStatus.OK);

        } catch (Exception e) {
            log.error("Por favor comuniquese con el administrador", e);
            return new ResponseEntity<>("Por favor comuniquese con el administrador",
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <b> Metodo que genera el reporte por fechas, la fecha(yyyy-MM-dd) esta separada por
     * coma(,). </b>
     * <p>
     * [Author: Jenny Pucha, Date: 21 abr. 2024]
     * </p>
     *
     * @param fecha fechas de entrada
     * @return ResponseEntity<?> lista o mensaje de error
     **/
    @GetMapping(value = "/reportes")
    public ResponseEntity<?> generarReporte(@RequestParam String identificacion,
        @RequestParam String fecha) {
        try {
            return new ResponseEntity<List<ReporteDto>>(service.generarReporte(identificacion, fecha),
            HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause().getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

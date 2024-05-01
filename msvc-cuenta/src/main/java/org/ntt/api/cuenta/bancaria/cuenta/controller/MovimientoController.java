/**
 *
 */
package org.ntt.api.cuenta.bancaria.cuenta.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.ntt.api.cuenta.bancaria.cuenta.controller.dto.entrada.MovimientoEntradaDto;
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
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
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
    private static final String ERROR_MN = "Ha ocurrido un error {}";

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
    public ResponseEntity<?> guardar(
        @Valid @RequestBody MovimientoEntradaDto movimientoEntradaDto, BindingResult resultado) {
        try {
            if (resultado.hasErrors()) {
                return validar(resultado);
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(movimientoEntradaDto));
        } catch (CuentaException e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.internalServerError().body(e.getCause().getMessage());
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
    public ResponseEntity<?> obtenerPorNumeroCuenta(@PathVariable int numeroCuenta)
        throws CuentaException {
        try {
            return ResponseEntity.ok().body(service.obtenerPorNumeroCuenta(numeroCuenta));
        }catch (Exception e){
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.internalServerError().body(e.getCause().getMessage());
        }
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
    public ResponseEntity<?> actualizar(
        @Valid @RequestBody MovimientoEntradaDto movimientoEntradaDto, BindingResult resultado){
        try {
            if (resultado.hasErrors()) {
                return validar(resultado);
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.update(movimientoEntradaDto));
        } catch (CuentaException e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.internalServerError().body(e.getCause().getMessage());
        }
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
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
        try {
            service.deleteByIdMovimiento(id);
            return ResponseEntity.ok().body("Registro Eliminado");
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.internalServerError().body(e.getCause().getMessage());
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
            return ResponseEntity.ok().body(service.generarReporte(identificacion, fecha));
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.internalServerError().body(e.getCause().getMessage());
        }
    }

    /**
     * <b> Metodo para tratar los errores de validaciones de los datos de entrada. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 19 abr. 2024]
     * </p>
     *
     * @param resultado parametro de entrada
     * @return ResponseEntity<?> lista o mensaje de error
     */
    private static ResponseEntity<Map<String, String>> validar(
        BindingResult resultado) {
        Map<String, String> errores = new HashMap<>();
        resultado.getFieldErrors().forEach(error -> {
            errores.put(error.getField(),
                "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}

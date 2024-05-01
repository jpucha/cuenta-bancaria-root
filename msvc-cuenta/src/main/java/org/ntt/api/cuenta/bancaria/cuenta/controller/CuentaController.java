/**
 *
 */
package org.ntt.api.cuenta.bancaria.cuenta.controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.ntt.api.cuenta.bancaria.cuenta.controller.dto.entrada.CuentaEntradaDto;
import org.ntt.api.cuenta.bancaria.cuenta.service.CuentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b> Clase controlador de las cuentas. </b>
 *
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Jenny Pucha $, $Date: 20 abr. 2024 $]
 *     </p>
 */
@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private static final Logger log = LoggerFactory.getLogger(CuentaController.class);
    private static final String ERROR_MN = "Ha ocurrido un error {}";

    @Autowired
    private CuentaService service;

    /**
     * <b> Metodo que crea cuentas. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 20 abr. 2024]
     * </p>
     *
     * @param cuentaEntradaDto parametro de entrada
     * @return ResponseEntity<?> objeto o mensaje de error
     */
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody CuentaEntradaDto cuentaEntradaDto,
        BindingResult resultado) {
        try {
            if (resultado.hasErrors()) {
                return validar(resultado);
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(cuentaEntradaDto));
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.internalServerError().body(e.getCause().getMessage());
        }
    }

    /**
     * <b> Metodo para obtener las cuentas de un cliente por su identificacion. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 20 abr. 2024]
     * </p>
     *
     * @param identificacion parametro de entrada
     * @return ResponseEntity<?> lista o mensaje de error
     **/
    @GetMapping(path = "/{identificacion}")
    public ResponseEntity<?> obtenerCuentasPorCliente(@PathVariable String identificacion) {
        try {
            return ResponseEntity.ok().body(service.obtenerCuentasPorCliente(identificacion));
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.internalServerError().body(e.getCause().getMessage());
        }
    }

    /**
     * <b> Metodo que actualiza la cuenta. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 20 abr. 2024]
     * </p>
     *
     * @param cuentaEntradaDto parametro de entrada
     * @return ResponseEntity<?> lista o mensaje de error
     */
    @PutMapping
    public ResponseEntity<?> actualizar(@Validated @RequestBody CuentaEntradaDto cuentaEntradaDto,
        BindingResult resultado) {
        try {
            if (resultado.hasErrors()) {
                return validar(resultado);
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(cuentaEntradaDto));
        } catch (Exception e) {
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
            service.delete(id);
            return ResponseEntity.ok().body("Registro Eliminado");
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

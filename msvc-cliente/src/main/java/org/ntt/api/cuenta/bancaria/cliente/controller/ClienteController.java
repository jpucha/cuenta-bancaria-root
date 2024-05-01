/**
 *
 */
package org.ntt.api.cuenta.bancaria.cliente.controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.ntt.api.cuenta.bancaria.cliente.controller.dto.entrada.ClienteEntradaDto;
import org.ntt.api.cuenta.bancaria.cliente.service.ClienteService;
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
 * <b> Clase controlador de los clientes. </b>
 *
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Jenny Pucha $, $Date: 21 abr. 2024 $]
 *     </p>
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);
    private static final String ERROR_MN = "Ha ocurrido un error {}";

    @Autowired
    private ClienteService service;

    /**
     * <b> Metodo que crea un cliente. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 19 abr. 2024]
     * </p>
     *
     * @param clienteEntradaDto parametro de entrada
     * @return ResponseEntity<?> objeto o mensaje de error
     */
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody ClienteEntradaDto clienteEntradaDto,
        BindingResult resultado) {
        try {
            if (resultado.hasErrors()) {
                return validar(resultado);
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(clienteEntradaDto));
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

    /**
     * <b> Metodo para obtiene un cliente por su identificacion. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 19 abr. 2024]
     * </p>
     *
     * @param identificacion parametro de entrada
     * @return ResponseEntity<?> objeto o mensaje de error
     */
    @GetMapping(path = "/{identificacion}")
    public ResponseEntity<?> obtenerClientePorIdentificacion(
        @Validated @PathVariable String identificacion) {
        try {
            return ResponseEntity.ok().body(service.obtenerPorIdentificacion(identificacion));
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.internalServerError().body(e.getCause().getMessage());
        }
    }

    /**
     * <b> Metodo para obtiene todos los clientes </b>
     * <p>
     * [Author: Jenny Pucha, Date: 19 abr. 2024]
     * </p>
     *
     * @return ResponseEntity<?> lista o mensaje de error
     */
    @GetMapping()
    public ResponseEntity<?> obtenerTodosClientes() {
        try {
            return ResponseEntity.ok().body(service.read());
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.internalServerError().body(e.getCause().getMessage());
        }
    }

    /**
     * <b> Metodo que actualiza un cliente. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 19 abr. 2024]
     * </p>
     *
     * @param clienteEntradaDto parametro de entrada
     * @return ResponseEntity<?> objeto o mensaje de error
     */
    @PutMapping
    public ResponseEntity<?> actualizar(@Valid @RequestBody ClienteEntradaDto clienteEntradaDto,
        BindingResult resultado) {
        try {
            if (resultado.hasErrors()) {
                return validar(resultado);
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.update(clienteEntradaDto));
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.internalServerError().body(e.getCause().getMessage());
        }
    }

    /**
     * <b> Metodo que elimina un registro por su id. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 19 abr. 2024]
     * </p>
     *
     * @param identificacion parametro de entrada
     * @return ResponseEntity<?> mensaje de error
     */
    @DeleteMapping("/{identificacion}")
    public ResponseEntity<?> eliminar(@PathVariable("identificacion") String identificacion) {
        try {
            service.delete(identificacion);
            return ResponseEntity.ok().body("Registro Eliminado");
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.internalServerError().body(e.getCause().getMessage());
        }
    }
}

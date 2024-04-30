/**
 *
 */
package org.ntt.api.cuenta.bancaria.cuenta.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.ntt.api.cuenta.bancaria.cuenta.controller.dto.CuentaEntradaDto;
import org.ntt.api.cuenta.bancaria.cuenta.enumeration.EstadoEmun;
import org.ntt.api.cuenta.bancaria.cuenta.model.ClienteModel;
import org.ntt.api.cuenta.bancaria.cuenta.model.entity.Cuenta;
import org.ntt.api.cuenta.bancaria.cuenta.service.CuentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
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

    @Autowired
    private CuentaService service;

    /**
     * <b> Metodo que crea cuentas. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 20 abr. 2024]
     * </p>
     *
     * @param cuentaEntradaDto parametro de entrada
     * @return ResponseEntity<?> lista o mensaje de error
     */
    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody CuentaEntradaDto cuentaEntradaDto) {
        try {

            Optional<ClienteModel> clienteEncontrado = null;
            if (!ObjectUtils.isEmpty(cuentaEntradaDto.getIdentificacion())) {
                clienteEncontrado = service.obtenerClientePorIdentificacion(
                    cuentaEntradaDto.getIdentificacion());
            }

            if (null != clienteEncontrado && clienteEncontrado.isPresent()) {
                ClienteModel cliente = clienteEncontrado.get();
                if (EstadoEmun.INACTIVO.getDescripcion().equals(cliente.getEstado())) {
                    return new ResponseEntity<>("El cliente se encuentra inactivo.",
                        HttpStatus.BAD_REQUEST);
                } else {
                    Cuenta cuenta = Cuenta.builder()
                        .estado(EstadoEmun.ACTIVO.getDescripcion())
                        .numeroCuenta(cuentaEntradaDto.getNumeroCuenta())
                        .saldoInicial(BigDecimal.valueOf(cuentaEntradaDto.getSaldoInicial()))
                        .tipoCuenta(cuentaEntradaDto.getTipoCuenta())
                        .idCliente(clienteEncontrado.get().getClienteId()).build();
                    Cuenta cuentaGuardada = service.create(cuenta);
                    return new ResponseEntity<Cuenta>(cuentaGuardada, HttpStatus.CREATED);
                }

            }
            return new ResponseEntity<>("No se encuentra registrado como cliente.",
                HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.error("Por favor comuniquese con el administrador", e);
            if(e.getLocalizedMessage().contains("400")){
                return new ResponseEntity<>("Cliente no encontrado.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(e.getLocalizedMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <b> Metodo para obtiene un cliente por su identidicacion. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 20 abr. 2024]
     * </p>
     *
     * @param identificacion parametro de entrada
     * @return ResponseEntity<?> lista o mensaje de error
     **/
    @GetMapping(path = "/{identificacion}")
    public ResponseEntity<?> obtenerCuentaPorCliente(@PathVariable String identificacion) {
        try {
            Optional<ClienteModel> clienteEncontrado = null;
            if (!ObjectUtils.isEmpty(identificacion)) {
                clienteEncontrado = service.obtenerClientePorIdentificacion(identificacion);
            }
            if (!clienteEncontrado.isPresent()) {
                return new ResponseEntity<>("No existe el cliente", HttpStatus.BAD_REQUEST);
            }
            List<Cuenta> listaCuenta = service.obtenerPorCliente(
                clienteEncontrado.get().getClienteId());
            if (null == listaCuenta || listaCuenta.isEmpty()) {
                return new ResponseEntity<>("No existe cuenta(s) para el numero de identificacion "
                    + clienteEncontrado.get().getIdentificacion(), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<List<Cuenta>>(listaCuenta, HttpStatus.OK);
            }

        } catch (Exception e) {
            log.error("Por favor comuniquese con el administrador", e);
            return new ResponseEntity<>(e.getLocalizedMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<?> update(@Validated @RequestBody CuentaEntradaDto cuentaEntradaDto) {
        try {
            if (cuentaEntradaDto.getNumeroCuenta() == 0) {
                return new ResponseEntity<>("Numero de cuenta no puede ser vacio.",
                    HttpStatus.BAD_REQUEST);
            }
            Optional<Cuenta> cuenta = service.obtenerPorNumeroCuenta(
                cuentaEntradaDto.getNumeroCuenta());
            if (cuenta.isPresent()) {
                Cuenta cuentaActualizar = cuenta.get();
                cuentaActualizar.setNumeroCuenta(cuentaEntradaDto.getNumeroCuenta());
                cuentaActualizar.setTipoCuenta(cuentaEntradaDto.getTipoCuenta());
                cuentaActualizar
                    .setSaldoInicial(
                        BigDecimal.valueOf(Double.valueOf(cuentaEntradaDto.getSaldoInicial())));
                cuentaActualizar.setEstado(cuentaEntradaDto.getEstado());
                Cuenta cuentaGuardada = service.update(cuentaActualizar);
                return new ResponseEntity<Cuenta>(cuentaGuardada, HttpStatus.OK);
            }
            return new ResponseEntity<>("La cuenta no existe.", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.error("Por favor comuniquese con el administrador", e);
            return new ResponseEntity<>(e.getLocalizedMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Optional<Cuenta> cuenta = service.obtenerPorId(id);
            if (cuenta.isPresent()) {
                service.delete(id);
                return new ResponseEntity<>("Registro Eliminado", HttpStatus.OK);
            }
            return new ResponseEntity<String>(
                "No se puede eliminar la cuenta con id: " + id + " no existe el registro",
                HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.error("Por favor comuniquese con el administrador", e);
            return new ResponseEntity<>(e.getLocalizedMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

/**
 *
 */
package org.ntt.api.cuenta.bancaria.cuenta.service.impl;

import java.util.List;
import java.util.Optional;
import org.ntt.api.cuenta.bancaria.cuenta.clients.ClienteClientRest;
import org.ntt.api.cuenta.bancaria.cuenta.model.ClienteModel;
import org.ntt.api.cuenta.bancaria.cuenta.model.entity.Cuenta;
import org.ntt.api.cuenta.bancaria.cuenta.repository.CuentaRepository;
import org.ntt.api.cuenta.bancaria.cuenta.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <b> Servicio para la Cuenta. </b>
 *
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Jenny Pucha $, $Date: 20 abr. 2024 $]
 *     </p>
 */
@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteClientRest clienteRest;

    @Override
    public Cuenta create(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public List<Cuenta> read() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta update(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public void delete(Long id) {
        cuentaRepository.deleteById(id);

    }

    @Override
    public Optional<Cuenta> obtenerPorId(Long id) {
        return cuentaRepository.findById(id);
    }

    @Override
    public List<Cuenta> obtenerPorCliente(Long id) {
        return cuentaRepository.findByIdCliente(id);
    }

    @Override
    public Optional<Cuenta> obtenerPorNumeroCuenta(int numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ClienteModel> obtenerClientePorIdentificacion(String id) {
        return clienteRest.obtenerClientePorIdentificacion(id);
    }

}

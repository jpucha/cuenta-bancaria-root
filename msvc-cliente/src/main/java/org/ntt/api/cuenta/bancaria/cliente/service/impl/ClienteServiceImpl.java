/**
 * 
 */
package org.ntt.api.cuenta.bancaria.cliente.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.ntt.api.cuenta.bancaria.cliente.model.entity.Cliente;
import org.ntt.api.cuenta.bancaria.cliente.repository.ClienteRepository;
import org.ntt.api.cuenta.bancaria.cliente.service.ClienteService;

/**
 * 
 * <b> Servicio para el cliente. </b>
 * 
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Pucha $, $Date: 19 abr. 2024 $]
 *          </p>
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente create(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> read() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente update(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void delete(Long id) {
		clienteRepository.deleteById(id);

	}

	@Override
	public Optional<Cliente> obtenerPorEstadoIdCliente(String estado, Long id) {
		return clienteRepository.findByEstadoAndClienteId(estado, id);
	}

	@Override
	public Optional<Cliente> obtenerPorId(Long id) {
		return clienteRepository.findById(id);
	}

	@Override
	public Optional<Cliente> obtenerPorIdCliente(Long id) {
		return clienteRepository.findByClienteId(id);
	}

	@Override
	public Optional<Cliente> obtenerPorIdentificacion(String identificacion) {
		return clienteRepository.findByIdentificacion(identificacion);
	}

}

/**
 * 
 */
package org.ntt.api.cuenta.bancaria.cliente.service;

import java.util.List;
import java.util.Optional;

import org.ntt.api.cuenta.bancaria.cliente.model.entity.Cliente;

/**
 * 
 * <b> Interfaz del servicio para el cliente. </b>
 * 
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Pucha $, $Date: 19 abr. 2024 $]
 *          </p>
 */
public interface ClienteService {
	Cliente create(Cliente cliente);

	List<Cliente> read();

	Cliente update(Cliente cliente);

	void delete(Long id);

	Optional<Cliente> obtenerPorEstadoIdCliente(String estado, Long id);

	Optional<Cliente> obtenerPorId(Long id);

	Optional<Cliente> obtenerPorIdCliente(Long id);

	// List<Cliente> obtenerPorIdPersona(Long id);

	Optional<Cliente> obtenerPorIdentificacion(String identificacion);

}

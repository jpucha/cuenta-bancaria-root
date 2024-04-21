/**
 * 
 */
package org.ntt.api.cuenta.bancaria.cliente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.ntt.api.cuenta.bancaria.cliente.model.entity.Cliente;

/**
 * 
 * <b> Interfaz del repositorio del cliente. </b>
 * 
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Pucha $, $Date: 19 abr. 2024 $]
 *          </p>
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByEstadoAndClienteId(String estado, Long id);

	Optional<Cliente> findByClienteId(Long id);

	Optional<Cliente> findByIdentificacion(String id);

}

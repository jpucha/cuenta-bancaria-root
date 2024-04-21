/**
 * 
 */
package org.ntt.api.cuenta.bancaria.cuenta.service;

import java.util.List;
import java.util.Optional;

import org.ntt.api.cuenta.bancaria.cuenta.model.ClienteModel;
import org.ntt.api.cuenta.bancaria.cuenta.model.entity.Cuenta;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 
 * <b> Interfaz del servicio para el Cuenta. </b>
 * 
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Pucha $, $Date: 20 abr. 2024 $]
 *          </p>
 */
public interface CuentaService {

	Cuenta create(Cuenta cuenta);

	List<Cuenta> read();

	Cuenta update(Cuenta cuenta);

	void delete(Long id);

	Optional<Cuenta> obtenerPorId(Long id);

	List<Cuenta> obtenerPorCliente(Long id);

	Optional<Cuenta> obtenerPorNumeroCuenta(int numeroCuenta);

	/**
	 * Obtiene el cliente comunicandose al ws.
	 * @param id, representa el numero de identificacion del cliente.
	 * @return ClienteModel.
	 **/
	Optional<ClienteModel> obtenerClientePorIdentificacion(String id);

}
